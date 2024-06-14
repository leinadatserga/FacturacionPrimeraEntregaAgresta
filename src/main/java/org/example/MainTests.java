package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;

public class MainTests {
    public static void main(String[] args) {

        GestorCliente gestorCliente = new GestorCliente();
        GestorProducto gestorProducto = new GestorProducto();
        GestorInvoice gestorInvoice = new GestorInvoice();
        GestorInvoiceDetail gestorInvoiceDetail = new GestorInvoiceDetail();

        gestorProducto.create("Domo antivandalico", 120.0, 48);
        gestorProducto.create("Bullet IR", 107.0, 80);
        gestorProducto.create("PTZ autotracking", 1120.0, 8);
        gestorProducto.create("NVR 8 canales", 250.0, 10);
        gestorProducto.create("Fuente multiple x10", 80.0, 100);
        gestorProducto.create("Monitor 32", 145.0, 19);
        gestorCliente.create("Juancho", "Pineda", 22697384, 22);
        gestorCliente.create("Pepa", "Armada", 28739377, 97);
        List<Client> clientes = gestorCliente.readAll();
        Client cliente = clientes.get(0); // Asumiendo que es el primer cliente
        System.out.println(cliente.getNombre() + " " + cliente.getApellido());
        List<Product> productos = gestorProducto.readAll();
        Product producto = productos.get(3);
        System.out.println(producto.getNombre());


        //Agregar productos al carrito del cliente
        for (int i = 0; i < 3; i++) {
            gestorProducto.addToCart(cliente, productos.get(i));
        }

        //Recargar cliente para obtener el carrito completo
        cliente = gestorCliente.readByIdWithCart(cliente.getId());

        //Mostrar productos en el carrito del cliente
        System.out.println("Productos en el carrito del cliente " + cliente.getNombre() + ":");
        for (Product product : cliente.getCart()) {
            System.out.println(product.getNombre());
        }

        //Crear una factura para el cliente
        double total = cliente.getCart().stream().mapToDouble(Product::getPrecio).sum();
        gestorInvoice.create(cliente, LocalDateTime.now(), total);
        Invoice invoice = gestorInvoice.readById(1); // Asumiendo que es la primera factura

        //Agregar detalles a la factura
        for (Product product : cliente.getCart()) {
            gestorInvoiceDetail.create(invoice, product, 1, product.getPrecio());
        }

        //Mostrar la factura y sus detalles en la consola
        showInvoiceDetails(invoice.getId());

        //Cerrar EntityManagerFactory
        GestorGenerico.closeEntityManagerFactory();
    }

    private static void showInvoiceDetails(Integer invoiceId) {

        EntityManager manager = GestorGenerico.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Invoice invoice = manager.find(Invoice.class, invoiceId);
            if (invoice != null) {
                System.out.println("Factura ID: " + invoice.getId());
                System.out.println("Cliente: " + invoice.getClient().getNombre() + " " + invoice.getClient().getApellido());
                System.out.println("Fecha de creación: " + invoice.getCreatedAt());
                System.out.println("Total: " + invoice.getTotal());
                System.out.println("Detalles:");

                for (InvoiceDetail detail : invoice.getDetails()) {
                    System.out.println(" - Producto: " + detail.getProduct().getNombre());
                    System.out.println("   Cantidad: " + detail.getAmount());
                    System.out.println("   Precio: " + detail.getPrice());
                    System.out.println("   Subtotal: " + (detail.getAmount() * detail.getPrice()));
                }
            } else {
                System.out.println("Factura no encontrada.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
}

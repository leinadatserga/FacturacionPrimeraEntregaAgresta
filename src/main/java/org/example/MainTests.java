package org.example;

import java.time.LocalDateTime;
import java.util.List;

public class MainTests {
    public static void main(String[] args) {

        GestorCliente gestorCliente = new GestorCliente();
        GestorProducto gestorProducto = new GestorProducto();
        GestorInvoice gestorInvoice = new GestorInvoice();
        GestorInvoiceDetail gestorInvoiceDetail = new GestorInvoiceDetail();

        //gestorProducto.create("Domo antivandalico", 120, 48);
        //gestorProducto.create("Bullet IR", 107, 80);
        //gestorProducto.create("PTZ autotracking", 1120, 8);
        //gestorProducto.create("NVR 8 canales", 250, 10);
        //gestorProducto.create("Fuente multiple x10", 80, 100);
        //gestorProducto.create("Monitor 32", 145, 19);
        //gestorCliente.create("Juancho", "Pineda", 22697384, 22);
        //gestorCliente.create("Pepa", "Armada", 28739377, 97);
        List<Client> clientes = gestorCliente.readAll();
        Client cliente = clientes.get(0); // Asumiendo que es el primer cliente
        System.out.println(cliente.getNombre() + " " + cliente.getApellido());
        List<Product> productos = gestorProducto.readAll();
        Product producto = productos.get(3);
        System.out.println(producto.getNombre());


        // Agregar productos al carrito del cliente
        for (int i = 0; i < 3; i++) {
            gestorProducto.addToCart(cliente, productos.get(i));
        }

        // Recargar cliente para obtener el carrito completo
        cliente = gestorCliente.readByIdWithCart(cliente.getId());

        // Mostrar productos en el carrito del cliente
        System.out.println("Productos en el carrito del cliente " + cliente.getNombre() + ":");
        for (Product product : cliente.getCart()) {
            System.out.println(product.getNombre());
        }

        // Crear una factura para el cliente
        gestorInvoice.create(cliente, LocalDateTime.now(), 600.0);
        Invoice invoice = gestorInvoice.readById(1); // Asumiendo que es la primera factura

        // Agregar detalles a la factura
        for (Product product : productos) {
            gestorInvoiceDetail.create(invoice, product, 1, product.getPrecio());
        }

        // Cerrar EntityManagerFactory
        GestorGenerico.closeEntityManagerFactory();
    }
}

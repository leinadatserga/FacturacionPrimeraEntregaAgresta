package org.example;



import jakarta.persistence.*;

import java.util.List;

public class GestorProducto {

    public void create(String nombre, Double precio, Integer stock) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Product product = new Product(nombre, precio, stock);
        manager.persist(product);
        manager.getTransaction().commit();
        manager.close();
    }
    public List<Product> readAll() {
        EntityManager manager = GestorGenerico.getEntityManager();
        List<Product> listado = manager.createQuery("From Product", Product.class).getResultList();
        manager.close();
        return listado;
    }
    public void addToCart(Client client, Product product) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Client managedClient = manager.find(Client.class, client.getId());
        Product managedProduct = manager.find(Product.class, product.getId());
        managedClient.addProduct(managedProduct);
        manager.getTransaction().commit();
        manager.close();
    }
    public void removeToCart(Client client, Product product) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        client.getCart().remove(product);
        manager.persist(product);
        manager.getTransaction().commit();
        manager.close();
    }
}


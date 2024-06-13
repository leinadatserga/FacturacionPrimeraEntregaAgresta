package org.example;



import jakarta.persistence.*;

import java.util.List;

public class GestorProducto {

    public void create(String nombre, Integer precio, Integer stock) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Product product = new Product(nombre, precio, stock);
        manager.persist(product);
        manager.getTransaction().commit();
        manager.close();
    }
    public List<Product> readAll() {
        EntityManager manager = GestorGenerico.getEntityManager();
        List<Product> listado = manager.createQuery("From Products", Product.class).getResultList();
        manager.close();
        return listado;
    }
    public void addToCart(Client client, Product product) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        client.getCart().add(product);
        manager.persist(product);
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


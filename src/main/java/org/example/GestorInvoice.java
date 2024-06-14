package org.example;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;

public class GestorInvoice {

    public void create(Client client, LocalDateTime createdAt, Double total) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Invoice invoice = new Invoice(client, createdAt, total);
        manager.persist(invoice);
        manager.getTransaction().commit();
        manager.close();
    }

    public Invoice readById(Integer id) {
        EntityManager manager = GestorGenerico.getEntityManager();
        Invoice invoice = manager.find(Invoice.class, id);
        manager.close();
        return invoice;
    }
}


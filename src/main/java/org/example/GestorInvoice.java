package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
        TypedQuery<Invoice> query = manager.createQuery(
                "SELECT i FROM Invoice i LEFT JOIN FETCH i.details WHERE i.id = :id", Invoice.class);
        query.setParameter("id", id);
        Invoice invoice = query.getSingleResult();
        manager.close();
        return invoice;
    }
}


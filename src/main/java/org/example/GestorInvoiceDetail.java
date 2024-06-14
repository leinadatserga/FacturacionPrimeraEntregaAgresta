package org.example;

import jakarta.persistence.EntityManager;

public class GestorInvoiceDetail {

    public void create(Invoice invoice, Product product, Integer amount, Double price) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        InvoiceDetail detail = new InvoiceDetail(invoice, product, amount, price);
        invoice.addDetail(detail);
        manager.persist(detail);
        manager.getTransaction().commit();
        manager.close();
    }
}


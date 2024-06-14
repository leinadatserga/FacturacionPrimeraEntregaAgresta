package org.example;

import jakarta.persistence.*;
import java.util.List;

public class GestorCliente {

    public void create(String nombre, String apellido, Integer documento, Integer edad) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Client client = new Client(nombre, apellido, documento, edad);
        manager.persist(client);
        manager.getTransaction().commit();
        manager.close();
    }
    public List<Client> readAll() {
        EntityManager manager = GestorGenerico.getEntityManager();
        List<Client> listado = manager.createQuery("From Client", Client.class).getResultList();
        manager.close();
        return listado;
    }
    public Client readById(Integer id) {
        EntityManager entityManager = GestorGenerico.getEntityManager();
        TypedQuery<Client> query = entityManager.createQuery(
                "SELECT c FROM Client c LEFT JOIN FETCH c.cart WHERE c.Id = :id", Client.class);
        query.setParameter("id", id);
        Client cliente = query.getSingleResult();
        entityManager.close();
        return cliente;
    }
    public Client readByIdWithCart(Integer id) {
        EntityManager entityManager = GestorGenerico.getEntityManager();
        TypedQuery<Client> query = entityManager.createQuery(
                "SELECT c FROM Client c LEFT JOIN FETCH c.cart WHERE c.Id = :id", Client.class);
        query.setParameter("id", id);
        Client cliente = query.getSingleResult();
        entityManager.close();
        return cliente;
    }
    public void update(Integer id, String nombre, String apellido, Integer documento, Integer edad) {
        EntityManager entityManager = GestorGenerico.getEntityManager();
        entityManager.getTransaction().begin();
        Client cliente = entityManager.find(Client.class, id);
        if (cliente != null) {
            if (nombre != null) { cliente.setNombre(nombre); }
            if (apellido != null) { cliente.setApellido(apellido); }
            if (documento != null) { cliente.setDocumento(documento); }
            if (edad != null) { cliente.setEdad(edad); }
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }
    public void delete(Integer id) {
        EntityManager entityManager = GestorGenerico.getEntityManager();
        entityManager.getTransaction().begin();
        Client cliente = entityManager.find(Client.class, id);
        if (cliente != null) {
            entityManager.remove(cliente);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }
}


package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private Double precio;

    @Column
    private Integer stock;

    @ManyToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<Client> clients;

    public Product() {
        this.clients = new ArrayList<>();
    }

    public Product(String nombre, Double precio, Integer stock) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(nombre, product.nombre) && Objects.equals(precio, product.precio) && Objects.equals(stock, product.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, stock);
    }
}



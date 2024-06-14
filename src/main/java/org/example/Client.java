package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Clients")
public class Client {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Integer documento;

    @Column
    private Integer edad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Client_Product",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> cart;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices = new ArrayList<>();

    public Client() {
        this.cart = new ArrayList<>();
    }
    public Client(String nombre, String apellido, Integer documento, Integer edad) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.edad = edad;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public void addProduct(Product product) {
        this.cart.add(product);
        product.getClients().add(this);
    }

    public void removeProduct(Product product) {
        this.cart.remove(product);
        product.getClients().remove(this);
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setClient(this);
    }

    public void removeInvoice(Invoice invoice) {
        invoices.remove(invoice);
        invoice.setClient(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(Id, client.Id) &&
                Objects.equals(nombre, client.nombre) &&
                Objects.equals(apellido, client.apellido) &&
                Objects.equals(documento, client.documento) &&
                Objects.equals(edad, client.edad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nombre, apellido, documento, edad);
    }
}


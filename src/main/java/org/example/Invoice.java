package org.example;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column
    private Double total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDetail> details = new ArrayList<>();

    public Invoice() {}

    public Invoice(Client client, LocalDateTime createdAt, Double total) {
        this.client = client;
        this.createdAt = createdAt;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<InvoiceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
    }

    public void addDetail(InvoiceDetail detail) {
        details.add(detail);
        detail.setInvoice(this);
    }

    public void removeDetail(InvoiceDetail detail) {
        details.remove(detail);
        detail.setInvoice(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) &&
                Objects.equals(client, invoice.client) &&
                Objects.equals(createdAt, invoice.createdAt) &&
                Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, createdAt, total);
    }
}


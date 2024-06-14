package org.example;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Invoice_Details")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Integer amount;

    @Column
    private Double price;

    public InvoiceDetail() {}

    public InvoiceDetail(Invoice invoice, Product product, Integer amount, Double price) {
        this.invoice = invoice;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDetail that = (InvoiceDetail) o;
        return Objects.equals(invoiceDetailId, that.invoiceDetailId) &&
                Objects.equals(invoice, that.invoice) &&
                Objects.equals(product, that.product) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceDetailId, invoice, product, amount, price);
    }
}


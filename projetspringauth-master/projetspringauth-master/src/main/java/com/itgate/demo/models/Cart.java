package com.itgate.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itgate.demo.models.Customer;
import com.itgate.demo.models.Produit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Long id;

    @ManyToMany
    @JsonProperty
    @Column
    private List<Produit> products;

    @OneToOne(mappedBy = "cart")
    @JsonProperty
    private Customer client;

    @Column
    @JsonProperty
    private BigDecimal total;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produit> getProducts() {
        return products;
    }

    public void setProducts(List<Produit> products) {
        this.products = products;
    }

    public void addProduct(Produit product) {
        if(products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        if(total == null) {
            total = new BigDecimal(0);
        }
        total = total.add(product.getPrice());
    }

    public void removeProduct(Produit product) {
        if(products == null) {
            products = new ArrayList<>();
        }
        products.remove(product);
        if(total == null) {
            total = new BigDecimal(0);
        }
        total = total.subtract(product.getPrice());
    }
}

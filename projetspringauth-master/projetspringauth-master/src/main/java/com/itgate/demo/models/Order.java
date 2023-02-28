package com.itgate.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="orders")
public class Order implements Serializable {


    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private Date date;
    private String description;
    private BigDecimal Total;

    @ManyToMany
    @JsonProperty
    @Column
    private List<Produit> products;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Customer client;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    @JsonIgnore
    public List<Produit> getProducts() {
        return products;
    }

    public void setProducts(List<Produit> products) {
        this.products = products;
    }


    public static Order createFromCart(Cart cart) {
        Order order = new Order();
        order.setProducts(cart.getProducts().stream().collect(Collectors.toList()));
        order.setTotal(cart.getTotal());
        order.setClient(cart.getClient());
        return order;
    }





}
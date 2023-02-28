package com.itgate.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="customers")

public class Customer extends User {

    @OneToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "client")
    private Collection<Order> orders;

    @JsonIgnore
    public Collection<Order> getOrders(){
        return orders;

    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

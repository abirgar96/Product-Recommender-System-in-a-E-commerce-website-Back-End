package com.itgate.demo.models;

import ch.qos.logback.core.joran.conditional.IfAction;
import jdk.nashorn.internal.ir.IfNode;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="produits")
public class Produit implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private BigDecimal price;
    private String description;
    private Boolean available;
    private Double quantite;
    private String picture;
    private Float weight;
    private String designation;

    @ManyToOne
    @JoinColumn(name = "id_cat")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider provider;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;

//        If(available == true){
//        return "In Stock";
//        Else { return ("Out of stock");}}
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getPicture() {
        return picture;
    }

//    public void setPicture(String picture) {
//        this.picture = picture;
//    }


    public void setPicture (String picture) {
        this.picture = picture;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
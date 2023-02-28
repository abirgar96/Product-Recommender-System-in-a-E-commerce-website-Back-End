package com.itgate.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModifyCartRequest {

    @JsonProperty
    private String username;

    @JsonProperty
    private Long itemId;

    @JsonProperty
    private int quantity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}


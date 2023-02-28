package com.itgate.demo.dao;

import com.itgate.demo.models.Cart;
import com.itgate.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByClient(Customer client);
}

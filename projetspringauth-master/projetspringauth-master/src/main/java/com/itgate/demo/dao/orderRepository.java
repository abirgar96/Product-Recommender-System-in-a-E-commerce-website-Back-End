package com.itgate.demo.dao;

import com.itgate.demo.models.Customer;
import com.itgate.demo.models.Order;
import com.itgate.demo.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface orderRepository extends JpaRepository<Order, Long> {

    @Query("select c from Order c where c.client.firstName = :client ")
    List<Order> findByClient(@Param("client") Customer client);

}

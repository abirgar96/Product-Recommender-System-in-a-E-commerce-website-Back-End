package com.itgate.demo.dao;

import com.itgate.demo.models.Customer;
import com.itgate.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface customerRepository extends JpaRepository<Customer, Long> {

    @Query("select m from Customer m where m.username = :username")
    Customer findByClientname(@Param("username") String username);

}

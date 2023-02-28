package com.itgate.demo.dao;

import com.itgate.demo.models.Category;
import com.itgate.demo.models.Customer;
import com.itgate.demo.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface categoryRepository extends JpaRepository<Category, Long> {
}

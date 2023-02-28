package com.itgate.demo.dao;

import com.itgate.demo.models.Provider;
import com.itgate.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface providerRepository extends JpaRepository<Provider, Long> {
}

package com.itgate.demo.dao;

import com.itgate.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<Admin, Long> {
}

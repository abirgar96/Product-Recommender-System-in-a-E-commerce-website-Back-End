package com.itgate.demo.dao;

import com.itgate.demo.models.Produit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface produitRepository extends JpaRepository  <Produit, Long> {



    @Query("select m from Produit m where m.name like :x")
    List<Produit> chercher(@Param("x") String x);

 @Query("select p from Produit p")
    Page<Produit> findByPage(Pageable pageable);

}

package com.itgate.demo.dao;

import com.itgate.demo.models.Customer;
import com.itgate.demo.models.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface editorRepository extends JpaRepository<Editor, Long> {
}

package com.itgate.demo.dao;

import com.itgate.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import java.sql.Blob;


@Repository
public interface IUser  extends JpaRepository<User,Long> {

    /*@Query("{'username':?0}")*/
     @Query("select u from  User  u where u.username= :username")
    User findByUsername(@Param("username") String username);


    @Query("select u from  User  u where u.email= :email")
    User findUserByEmail(@Param("email") String email ) ;

    @Query("select u from  User  u where u.PasswordResetToken= :resetLink")
    User findUserByResetLink(@Param("resetLink") String resetLink ) ;


}

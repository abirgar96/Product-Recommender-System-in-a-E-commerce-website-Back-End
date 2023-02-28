package com.itgate.demo.models;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin extends User {
}

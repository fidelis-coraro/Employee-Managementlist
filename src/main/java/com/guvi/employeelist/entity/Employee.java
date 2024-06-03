package com.guvi.employeelist.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String firstName;
    private  String lastName;
    private  String email;


}

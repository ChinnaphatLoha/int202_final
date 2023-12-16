package com.example.int202javassrpreexam.model;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e"),
        @NamedQuery(name = "Employee.findById", query = "select e from Employee e where e.id = :id"),
        @NamedQuery(name = "Employee.findByEmail", query = "select e from Employee e where e.email = :email"),
})
@ToString
public class Employee {

    @Id
    @Column(name = "employeeNumber", nullable = false)
    private Integer id;
    private String firstName;
    private String lastName;
    private String extension;
    private String email;
    @ManyToOne
    @JoinColumn(name = "officeCode")
    private Office office;
    private String password;
}

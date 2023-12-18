package com.example.int202javassrpreexam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "offices")
@Setter
@Getter
@NamedQueries({
        @NamedQuery(name = "Office.findAll", query = "select o from Office o"),
        @NamedQuery(name = "Office.findById", query = "select o from Office o where o.id = :id"),
        @NamedQuery(name = "Office.getEmployees", query = "select e.employees from Office e where e.id = :id")
})
@ToString(exclude = "employees")
public class Office {
    @Id
    @Column(name = "officeCode", nullable = false)
    private String id;
    private String city;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String postalCode;
    private String territory;
    @OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Employee> employees;
}

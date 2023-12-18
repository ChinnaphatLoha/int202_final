package com.example.int202javassrpreexam.repository;

import com.example.int202javassrpreexam.model.Employee;
import com.example.int202javassrpreexam.model.Office;
import com.example.int202javassrpreexam.utils.EntityManagerBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Collections;
import java.util.List;

public class OfficeRepository {
    private EntityManager em;
    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public List<Office> findAll() {
        return getEntityManager().createNamedQuery("Office.findAll", Office.class).getResultList();
    }

    public Office findById(String id) {
        return getEntityManager().createNamedQuery("Office.findById", Office.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Employee> getEmployeeList(String officeId) {
        return getEntityManager().createNamedQuery("Office.getEmployees", Employee.class)
                .setParameter("id", officeId)
                .getResultList();
    }
}

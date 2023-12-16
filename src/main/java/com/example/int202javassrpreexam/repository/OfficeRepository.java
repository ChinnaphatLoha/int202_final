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
        return Collections.emptyList();
    }

    public Office findById(String id) {
        return null;
    }

    public List<Employee> getEmployeeList(String officeId) {
        return Collections.emptyList();
    }
}

package com.example.int202javassrpreexam.repository;

import com.example.int202javassrpreexam.model.Employee;
import com.example.int202javassrpreexam.utils.EntityManagerBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private EntityManager em;

    public EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public List<Employee> findAll() {
        return getEntityManager().createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public Employee findById(Integer employeeId) {
        return getEntityManager()
                .createNamedQuery("Employee.findById", Employee.class)
                .setParameter("id", employeeId)
                .getSingleResult();
    }

    public Optional<Employee> findByEmail(String email) {
        try {
            Employee employee = getEntityManager()
                    .createNamedQuery("Employee.findByEmail", Employee.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(employee);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void create(Employee employee) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

//    public void updateReportsToForEmployee(Integer currentManagerId, Integer newManagerId) {
//        EntityManager em = getEntityManager();
//        em.getTransaction().begin();
//        em.createNamedQuery("Employee.updateReportTo")
//                .setParameter("newManagerId", newManagerId)
//                .setParameter("currentManagerId", currentManagerId)
//                .executeUpdate();
//        em.getTransaction().commit();
//    }

    public void delete(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Deleted Employee must not have null id");
        }

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(employee);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }
}

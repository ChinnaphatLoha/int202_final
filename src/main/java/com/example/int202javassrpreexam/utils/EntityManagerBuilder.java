package com.example.int202javassrpreexam.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.int202javassrpreexam.constants.Constants;

public class EntityManagerBuilder {
    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        return emf.createEntityManager();
    }
}

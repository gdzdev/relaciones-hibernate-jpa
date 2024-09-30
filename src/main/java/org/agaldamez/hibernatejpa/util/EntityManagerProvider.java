package org.agaldamez.hibernatejpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {

    // Creamos una instancia estática de EntityManagerFactory, que es responsable de crear EntityManagers.
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    // Método privado para construir el EntityManagerFactory utilizando la configuración de persistencia.
    private static EntityManagerFactory buildEntityManagerFactory() {
        // Creamos y retornamos el EntityManagerFactory con el nombre de unidad de persistencia "testJPA".
        return Persistence.createEntityManagerFactory("testJPA");
    }

    // Método público estático para obtener una nueva instancia de EntityManager.
    public static EntityManager getEntityManager() {
        // Creamos y retornamos un nuevo EntityManager a partir del EntityManagerFactory.
        return entityManagerFactory.createEntityManager();
    }
}


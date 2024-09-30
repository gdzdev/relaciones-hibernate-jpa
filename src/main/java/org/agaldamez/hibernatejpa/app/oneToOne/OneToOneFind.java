package org.agaldamez.hibernatejpa.app.oneToOne;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.ClienteDetalle;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToOneFind {

        public static void main (String[] args) {

                // Obtenemos un EntityManager para gestionar la conexión y las operaciones con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos un cliente existente en la base de datos utilizando su ID (1L)
                        Cliente cliente = em.find(Cliente.class, 1L);

                        // Creamos un nuevo objeto ClienteDetalle con información relevante
                        ClienteDetalle detalle = new ClienteDetalle(false, 645L);
                        em.persist(detalle); // Persistimos el nuevo detalle en la base de datos

                        // Establecemos la relación uno a uno entre el cliente encontrado y su nuevo detalle
                        cliente.setDetalle(detalle);

                        // Confirmamos la transacción, guardando los cambios realizados
                        em.getTransaction().commit();
                } catch (Exception e) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos los cambios realizados en la transacción
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar los recursos
                }
        }
}


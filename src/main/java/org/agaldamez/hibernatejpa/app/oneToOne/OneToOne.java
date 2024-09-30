package org.agaldamez.hibernatejpa.app.oneToOne;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.ClienteDetalle;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToOne {

        public static void main (String[] args) {

                // Obtenemos un EntityManager para interactuar con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Creamos un nuevo cliente
                        Cliente cliente = new Cliente("Catarina", "Doe", "Efectivo");
                        em.persist(cliente); // Persistimos el cliente en la base de datos

                        // Creamos un nuevo detalle para el cliente
                        ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
                        em.persist(detalle); // Persistimos el detalle en la base de datos

                        // Establecemos la relación uno a uno entre el cliente y su detalle
                        cliente.setDetalle(detalle);

                        em.getTransaction().commit(); // Confirmamos la transacción
                } catch (Exception e) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos
                }
        }
}


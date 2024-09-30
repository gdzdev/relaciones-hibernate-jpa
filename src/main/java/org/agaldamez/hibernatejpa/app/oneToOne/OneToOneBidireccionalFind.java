package org.agaldamez.hibernatejpa.app.oneToOne;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.ClienteDetalle;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToOneBidireccionalFind {

        public static void main (String[] args) {

                // Obtenemos un EntityManager para interactuar con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos un cliente existente en la base de datos por su ID (4L)
                        Cliente cliente = em.find(Cliente.class, 4L);
                        // Creamos un nuevo detalle para el cliente
                        ClienteDetalle detalle = new ClienteDetalle(true, 80000L);

                        // Establecemos la relación uno a uno entre el cliente existente y su nuevo detalle
                        cliente.addDetalle(detalle);

                        // Persistimos el cliente, lo que actualizará la relación con el nuevo detalle
                        em.getTransaction().commit(); // Confirmamos la transacción

                } catch (Exception e) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos
                }
        }
}

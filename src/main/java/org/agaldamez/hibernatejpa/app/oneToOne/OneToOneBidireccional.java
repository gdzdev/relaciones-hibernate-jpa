package org.agaldamez.hibernatejpa.app.oneToOne;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.ClienteDetalle;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToOneBidireccional {

        public static void main (String[] args) {

                // Obtenemos un EntityManager para interactuar con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Creamos un nuevo cliente con sus atributos
                        Cliente cliente = new Cliente("Luna", "Lunera", "Deposito");
                        // Creamos un nuevo detalle para el cliente
                        ClienteDetalle detalle = new ClienteDetalle(true, 80000L);

                        // Establecemos la relación uno a uno entre el cliente y su detalle
                        cliente.addDetalle(detalle);

                        // Persistimos el cliente, lo que también persiste el detalle debido a la relación
                        em.persist(cliente);

                        em.getTransaction().commit(); // Confirmamos la transacción
                } catch (Exception e) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos
                }
        }
}


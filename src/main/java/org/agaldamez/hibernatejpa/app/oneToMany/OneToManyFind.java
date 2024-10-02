package org.agaldamez.hibernatejpa.app.oneToMany;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.Direccion;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToManyFind {

        public static void main(String[] args) {
                // Relación Uno a Muchos
                // Obtenemos un EntityManager para interactuar con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos un cliente existente en la base de datos por su ID
                        Cliente cliente = em.find(Cliente.class, 5L);

                        // Creamos nuevas direcciones para el cliente
                        Direccion d1 = new Direccion("Calle Maria", 23);
                        Direccion d2 = new Direccion("MotoCross", 25);

                        // Agregamos las direcciones a la lista de direcciones del cliente
                        cliente.getDirecciones().add(d1);
                        cliente.getDirecciones().add(d2);

                        em.merge(cliente); // Sincronizamos el estado del cliente con la base de datos

                        em.getTransaction().commit(); // Confirmamos la transacción

                        System.out.println(cliente); // Imprimimos el cliente con sus direcciones

                        em.getTransaction().begin(); // Iniciamos una nueva transacción para modificar datos

                        // Buscamos una dirección existente en la base de datos por su ID
                        d1 = em.find(Direccion.class, 1L);
                        // Eliminamos la dirección de la lista de direcciones del cliente
                        cliente.getDirecciones().remove(d1);
                        System.out.println(cliente); // Imprimimos el cliente actualizado

                        em.getTransaction().commit(); // Confirmamos la transacción
                } catch (Exception err) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
                        err.printStackTrace(System.out); // Imprimimos el error en consola
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos
                }
        }
}


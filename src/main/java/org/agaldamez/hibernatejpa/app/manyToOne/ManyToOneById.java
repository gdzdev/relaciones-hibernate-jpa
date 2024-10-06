package org.agaldamez.hibernatejpa.app.manyToOne;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.Factura;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class ManyToOneById {

        public static void main(String[] args) {
                // Relación muchos a uno
                // Obtenemos un EntityManager para interactuar con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos un cliente en la base de datos por su ID (3L)
                        Cliente cliente = em.find(Cliente.class, 3L);

                        // Creamos una nueva factura asociada al cliente encontrado
                        Factura factura = new Factura("Maquillaje", 10000L);
                        factura.setCliente(cliente); // Establecemos la relación con el cliente
                        em.persist(factura); // Persistimos la factura en la base de datos

                        System.out.println(factura); // Imprimimos la factura para verificar la creación

                        em.getTransaction().commit(); // Confirmamos la transacción (se persisten los cambios en la base de datos)
                } catch (Exception err) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
                        err.printStackTrace(System.out); // Imprimimos el error en consola
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos
                }
        }
}


package org.agaldamez.hibernatejpa.app.oneToMany;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.Factura;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToManyBidireccional {

    public static void main(String[] args) {
        // Relación Uno a Muchos Bidireccional
        // Obtenemos un EntityManager para interactuar con la base de datos.
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            em.getTransaction().begin(); // Iniciamos una nueva transacción

            // Creamos un nuevo cliente
            Cliente cliente = new Cliente("Jose", "Alvarez", "deposito");
            // Creamos facturas asociadas al cliente
            Factura f1 = new Factura("tecnologia", 5000L);
            Factura f2 = new Factura("superMercado", 9550L);

            // Agregamos las facturas al cliente
            cliente.addFactura(f1);
            cliente.addFactura(f2);

            em.persist(cliente); // Persistimos el cliente y sus facturas en la base de datos

            em.getTransaction().commit(); // Confirmamos la transacción
            System.out.println(cliente); // Imprimimos el cliente para verificar la creación
        } catch (Exception err) {
            em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
            err.printStackTrace(System.out); // Imprimimos el error en consola
        } finally {
            em.close(); // Cerramos el EntityManager para liberar recursos
        }
    }
}


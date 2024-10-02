package org.agaldamez.hibernatejpa.app.oneToMany;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.Factura;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToManyBidireccionalFind {

    public static void main(String[] args) {
        // Relación Uno a Muchos Bidireccional
        // Obtenemos un EntityManager para interactuar con la base de datos.
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            em.getTransaction().begin(); // Iniciamos una nueva transacción

            // Buscamos un cliente existente en la base de datos por su ID
            Cliente cliente = em.find(Cliente.class, 1L);
            // Creamos nuevas facturas asociadas al cliente
            Factura f1 = new Factura("tecnologia", 5000L);
            Factura f2 = new Factura("superMercado", 9550L);

            // Agregamos las facturas al cliente
            cliente.addFactura(f1);
            cliente.addFactura(f2);

            // Si fuera necesario, se podría usar em.merge(cliente); para sincronizar el estado del cliente
            em.getTransaction().commit(); // Confirmamos la transacción

            em.getTransaction().begin(); // Iniciamos una nueva transacción para modificar datos

            // Buscamos una factura existente en la base de datos por su ID
            Factura facturaEliminar = em.find(Factura.class, 1L);
            // Eliminamos la factura del cliente
            cliente.removeFactura(facturaEliminar);

            em.getTransaction().commit(); // Confirmamos la transacción
            System.out.println(cliente); // Imprimimos el cliente actualizado
        } catch (Exception err) {
            em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
            err.printStackTrace(System.out); // Imprimimos el error en consola
        } finally {
            em.close(); // Cerramos el EntityManager para liberar recursos
        }
    }
}


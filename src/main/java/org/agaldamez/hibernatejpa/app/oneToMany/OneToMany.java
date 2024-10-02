package org.agaldamez.hibernatejpa.app.oneToMany;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.entity.Direccion;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class OneToMany {

    public static void main(String[] args) {
        // Relación Uno a Muchos
        // Obtenemos un EntityManager para interactuar con la base de datos.
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            em.getTransaction().begin(); // Iniciamos una nueva transacción

            // Creamos un nuevo cliente
            Cliente cliente = new Cliente("Catarina", "Flores", "efectivo");
            // Creamos direcciones asociadas al cliente
            Direccion d1 = new Direccion("Calle Maria", 23);
            Direccion d2 = new Direccion("MotoCross", 25);
            // Agregamos las direcciones al cliente
            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            em.persist(cliente); // Persistimos el cliente y sus direcciones en la base de datos
            em.getTransaction().commit(); // Confirmamos la transacción
            System.out.println(cliente); // Imprimimos el cliente para verificar la creación

            em.getTransaction().begin(); // Iniciamos una nueva transacción para modificar datos

            // Buscamos el cliente en la base de datos por su ID
            cliente = em.find(Cliente.class, cliente.getId());
            // Buscamos una dirección existente por su ID
            Direccion direccionBuscar = em.find(Direccion.class, 1L);
            // Eliminamos la dirección del cliente
            cliente.getDirecciones().remove(direccionBuscar);

            em.getTransaction().commit(); // Confirmamos la transacción (se persisten los cambios)
            System.out.println(cliente); // Imprimimos el cliente actualizado
        } catch (Exception err) {
            em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
            err.printStackTrace(System.out); // Imprimimos el error en consola
        } finally {
            em.close(); // Cerramos el EntityManager para liberar recursos
        }
    }
}


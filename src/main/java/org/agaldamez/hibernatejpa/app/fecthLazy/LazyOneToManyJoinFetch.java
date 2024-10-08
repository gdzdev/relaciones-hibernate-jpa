package org.agaldamez.hibernatejpa.app.fecthLazy;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class LazyOneToManyJoinFetch {

        public static void main (String[] args) {

                // Obtenemos un EntityManager para interactuar con la base de datos
                EntityManager em = EntityManagerProvider.getEntityManager();

                // Consulta que carga un cliente junto con sus direcciones y detalles
                // "LEFT JOIN FETCH" asegura que las relaciones se carguen de inmediato
                Cliente cliente = em.createQuery("SELECT c FROM Cliente c LEFT JOIN FETCH c.direcciones LEFT JOIN FETCH c.detalle Where c.id = :id",
                                Cliente.class)
                        .setParameter("id", 1L) // Asignamos el ID del cliente
                        .getSingleResult();     // Obtenemos el cliente con sus relaciones cargadas

                // Imprimimos el nombre del cliente y sus direcciones
                System.out.println(cliente.getNombre() + ", " + cliente.getDirecciones());

                // Imprimimos el nombre del cliente y sus detalles
                System.out.println(cliente.getNombre() + ", " + cliente.getDetalle());

                // Cerramos el EntityManager para liberar recursos
                em.close();
        }
}



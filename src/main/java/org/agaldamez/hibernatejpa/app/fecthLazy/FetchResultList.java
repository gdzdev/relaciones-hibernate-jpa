package org.agaldamez.hibernatejpa.app.fecthLazy;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

import java.util.List;

public class FetchResultList {

        public static void main (String[] args) {

                // Obtiene una instancia de EntityManager para gestionar las operaciones con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                // Ejecuta una consulta JPQL que selecciona todos los clientes junto con sus direcciones y detalles.
                // "Distinct" evita que los resultados se dupliquen si un cliente tiene múltiples direcciones o detalles.
                // "Left Join Fetch" se utiliza para cargar las relaciones en una sola consulta y evitar problemas de lazy loading.
                List<Cliente> clientes = em.createQuery("Select Distinct c From Cliente c Left Join Fetch c.direcciones Left Join Fetch c.detalle", Cliente.class)
                        .getResultList();

                // Itera sobre la lista de clientes obtenidos y muestra el nombre del cliente junto con sus direcciones y detalles.
                clientes.forEach(cliente -> {
                        System.out.println(cliente.getNombre() + ", direcciones: " + cliente.getDirecciones() + ", Detalle: " + cliente.getDetalle());
                });

                // Cierra el EntityManager para liberar los recursos.
                em.close();
        }
}


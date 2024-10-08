package org.agaldamez.hibernatejpa.app.fecthLazy;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Cliente;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class LazyOneToMany {

        public static void main (String[] args) {

                // Obtiene una instancia de EntityManager para realizar operaciones con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                // Busca en la base de datos una entidad Cliente por su ID (1L en este caso).
                // Este método cargará solo los datos de la entidad Cliente, pero no sus relaciones de tipo "OneToMany" de forma inmediata debido a la carga "Lazy".
                Cliente cliente = em.find(Cliente.class, 1L);

                // Aquí se intenta acceder a la relación 'direcciones' del cliente.
                // Si la relación 'direcciones' está marcada con la estrategia de carga "Lazy", las direcciones no se cargarán hasta que se intente acceder a ellas (lo que ocurre en este punto).
                // Esto podría causar un "LazyInitializationException" si se cierra el EntityManager antes de acceder a los datos asociados.
                System.out.println(cliente.getDirecciones());

                // Cierra el EntityManager para liberar los recursos.
                em.close();
        }
}


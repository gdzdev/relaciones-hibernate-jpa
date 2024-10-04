package org.agaldamez.hibernatejpa.app.manyToMany;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Alumno;
import org.agaldamez.hibernatejpa.entity.Curso;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class ManyToManyFind {

        public static void main (String[] args) {

                // Obtenemos un EntityManager para interactuar con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos instancias de Alumno en la base de datos por su ID
                        Alumno alumno1 = em.find(Alumno.class, 1L); // Carga el alumno con ID 1
                        Alumno alumno2 = em.find(Alumno.class, 2L); // Carga el alumno con ID 2

                        // Creamos un nuevo curso para el alumno 1
                        Curso curso1 = new Curso("Hibernate ", "Andres"); // Nuevo curso instanciado
                        // Buscamos un curso existente en la base de datos por su ID
                        Curso curso2 = em.find(Curso.class, 2L); // Carga el curso con ID 2

                        // Asociamos cursos al alumno 1 y alumno 2
                        alumno1.getCursos().add(curso1); // Agregamos el nuevo curso al alumno 1
                        alumno1.getCursos().add(curso2);  // Agregamos un curso existente al alumno 1
                        alumno2.getCursos().add(curso2);  // Agregamos el curso existente al alumno 2

                        em.getTransaction().commit(); // Confirmamos la transacción (se persisten los cambios en la base de datos)

                        // Imprimimos la información de los alumnos para verificar las asociaciones
                        System.out.println(alumno1); // Muestra el estado del alumno 1
                        System.out.println(alumno2); // Muestra el estado del alumno 2
                } catch (Exception e) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos
                }
        }
}


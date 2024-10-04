package org.agaldamez.hibernatejpa.app.manyToMany;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Alumno;
import org.agaldamez.hibernatejpa.entity.Curso;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class ManyToMany {

        public static void main (String[] args) {
                // Obtenemos un EntityManager para interactuar con la base de datos
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos la transacción

                        // Creamos dos alumnos
                        Alumno alumno1 = new Alumno("Aleks", "Galdamez");
                        Alumno alumno2 = new Alumno("Sofia", "Rocco");

                        // Creamos dos cursos
                        Curso curso1 = new Curso("Hibernate", "Andres");
                        Curso curso2 = new Curso("Clean Code", "Midu");

                        // Asociamos cursos a los alumnos
                        alumno1.getCursos().add(curso1);
                        alumno1.getCursos().add(curso2);
                        alumno2.getCursos().add(curso1);
                        alumno2.getCursos().add(curso2);

                        // Persistimos los alumnos (también se persisten sus cursos debido a la relación)
                        em.persist(alumno1);
                        em.persist(alumno2);

                        em.getTransaction().commit(); // Confirmamos la transacción
                        System.out.println(alumno1); // Imprimimos información del alumno 1
                        System.out.println(alumno2); // Imprimimos información del alumno 2

                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos un curso existente por su ID
                        Curso cursoExiste = em.find(Curso.class, 4L);

                        // Removemos el curso encontrado de los cursos del alumno 1
                        alumno1.getCursos().remove(cursoExiste);
                        System.out.println(alumno1); // Imprimimos el estado actualizado del alumno 1

                        em.getTransaction().commit(); // Confirmamos la transacción
                } catch (Exception e) {
                        em.getTransaction().rollback(); // Revertimos la transacción en caso de error
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos
                }
        }
}


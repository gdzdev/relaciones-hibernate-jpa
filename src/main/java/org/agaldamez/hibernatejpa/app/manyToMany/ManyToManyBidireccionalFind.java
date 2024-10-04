package org.agaldamez.hibernatejpa.app.manyToMany;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Alumno;
import org.agaldamez.hibernatejpa.entity.Curso;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

public class ManyToManyBidireccionalFind {

        public static void main (String[] args) {

                // Obtenemos un EntityManager para interactuar con la base de datos.
                EntityManager em = EntityManagerProvider.getEntityManager();

                try {
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos instancias de Alumno y Curso en la base de datos por su ID
                        Alumno alumno1 = em.find(Alumno.class, 1L); // Carga el alumno con ID 1
                        Alumno alumno2 = em.find(Alumno.class, 2L); // Carga el alumno con ID 2
                        Curso curso1 = em.find(Curso.class, 1L);   // Carga el curso con ID 1
                        Curso curso2 = em.find(Curso.class, 2L);   // Carga el curso con ID 2

                        // Asociamos cursos a los alumnos utilizando métodos que gestionan la relación
                        alumno1.addCurso(curso1);
                        alumno1.addCurso(curso2);
                        alumno2.addCurso(curso1);

                        em.getTransaction().commit(); // Confirmamos la transacción (se persisten los cambios en la base de datos)

                        // Imprimimos la información de los alumnos para verificar la asociación
                        System.out.println(alumno1);
                        System.out.println(alumno2);
                        em.getTransaction().begin(); // Iniciamos una nueva transacción

                        // Buscamos un curso existente en la base de datos por su ID
                        Curso cursoExiste = em.find(Curso.class, 3L); // Carga el curso con ID 3

                        // Cambiamos el ID de curso1 (esto es un ejemplo y no es una práctica recomendada en producción)
                        curso1.setId(2L); // Esto puede ser problemático si el ID ya está en uso

                        // Removemos el curso encontrado de la lista de cursos del alumno 1
                        alumno1.removeCurso(cursoExiste);
                        System.out.println(alumno1); // Imprimimos el estado actualizado del alumno 1

                        em.getTransaction().commit(); // Confirmamos la transacción (se aplican los cambios)
                } catch (Exception e) {
                        em.getTransaction().rollback(); // Si ocurre un error, revertimos la transacción
                } finally {
                        em.close(); // Cerramos el EntityManager para liberar recursos y evitar fugas de memoria
                }
        }
}


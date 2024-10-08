package org.agaldamez.hibernatejpa.app.fecthLazy;

import jakarta.persistence.EntityManager;
import org.agaldamez.hibernatejpa.entity.Alumno;
import org.agaldamez.hibernatejpa.util.EntityManagerProvider;

import java.util.List;

public class FetchManyToMany {

        public static void main (String[] args) {

                // Obtiene una instancia de EntityManager para manejar las operaciones de persistencia.
                EntityManager em = EntityManagerProvider.getEntityManager();

                // Ejecuta una consulta JPQL que selecciona todos los alumnos con sus cursos asociados.
                // "Distinct" evita duplicados, "Left Join fetch" asegura que los cursos relacionados se carguen en la misma consulta.
                List<Alumno> alumnos = em.createQuery("Select Distinct alum From Alumno alum Left Join fetch alum.cursos", Alumno.class)
                        .getResultList();

                // Itera sobre la lista de alumnos obtenidos y muestra el nombre del alumno junto con los cursos asociados.
                alumnos.forEach(alumno -> {
                        System.out.println(alumno.getNombre() + ", cursos: " + alumno.getCursos());
                });

                // Cierra el EntityManager para liberar los recursos.
                em.close();
        }
}


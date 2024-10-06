package org.agaldamez.hibernatejpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "cursos") // Define el nombre de la tabla en la base de datos
public class Curso {

        @Id // Indica que este campo es la clave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID de manera automática en la base de datos
        private Long id;

        private String titulo; // Título del curso
        private String profesor; // Nombre del profesor del curso

        @ManyToMany(mappedBy = "cursos") // Relación muchos a muchos con alumnos
        private List<Alumno> alumnos; // Lista de alumnos inscritos en el curso

        public Curso() {
                this.alumnos = new ArrayList<>(); // Inicializa la lista de alumnos
        }

        public Curso(String titulo, String profesor) {
                this(); // Llama al constructor por defecto
                this.titulo = titulo; // Asigna el título del curso
                this.profesor = profesor; // Asigna el nombre del profesor
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public String getProfesor() {
                return profesor;
        }

        public void setProfesor(String profesor) {
                this.profesor = profesor;
        }

        public List<Alumno> getAlumnos() {
                return alumnos;
        }

        public void setAlumnos(List<Alumno> alumnos) {
                this.alumnos = alumnos;
        }

        public void addAlumno(Alumno alumno) {
                if (!this.alumnos.contains(alumno)) {
                        this.alumnos.add(alumno); // Agrega el alumno a la lista
                        alumno.addCurso(this); // Agrega este curso al alumno
                }
        }

        public void removeAlumno(Alumno alumno) {
                if (this.alumnos.contains(alumno)) {
                        this.alumnos.remove(alumno); // Remueve el alumno de la lista
                        alumno.removeCurso(this); // Remueve este curso del alumno
                }
        }

        @Override
        public String toString() { // Representación en cadena de la entidad
                return "Curso{" +
                        "id=" + id +
                        ", titulo='" + titulo + '\'' +
                        ", profesor='" + profesor + '\'' +
                        '}';
        }
}


package org.agaldamez.hibernatejpa.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "alumnos") // Define el nombre de la tabla en la base de datos
public class Alumno { // Clase principal

        @Id // Indica que este campo es la clave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID de manera automática en la base de datos
        private Long id;

        private String nombre; // Nombre del alumno
        private String apellido; // Apellido del alumno

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Relación muchos a muchos con cascada para persistir y fusionar
        @JoinTable( // Personalizamos la tabla intermedia para la relación
                name = "tbl_alumnos_cursos", // Nombre de la tabla intermedia
                joinColumns = @JoinColumn(name = "alumno_id"), // Columna que hace referencia al alumno
                inverseJoinColumns = @JoinColumn(name = "curso_id"), // Columna que hace referencia al curso
                uniqueConstraints = @UniqueConstraint(columnNames = {"alumno_id", "curso_id"}) // Restringe combinaciones únicas de alumno y curso
        )
        private List<Curso> cursos; // Lista de cursos a los que está inscrito el alumno

        public Alumno() {
                this.cursos = new ArrayList<>(); // Inicializa la lista de cursos
        }

        public Alumno(String nombre, String apellido) {
                this(); // Llama al constructor por defecto
                this.nombre = nombre; // Asigna el nombre
                this.apellido = apellido; // Asigna el apellido
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public String getApellido() {
                return apellido;
        }

        public void setApellido(String apellido) {
                this.apellido = apellido;
        }

        public List<Curso> getCursos() {
                return cursos;
        }

        public void setCursos(List<Curso> cursos) {
                this.cursos = cursos;
        }

        public void addCurso(Curso curso) { // Método para agregar un curso
                if (!this.cursos.contains(curso)) { // Verifica que el curso no esté ya en la lista
                        this.cursos.add(curso); // Agrega el curso
                        curso.addAlumno(this); // Establece la relación inversa
                }
        }

        public void removeCurso(Curso curso) { // Método para remover un curso
                if (this.cursos.contains(curso)) { // Verifica que el curso esté en la lista
                        this.cursos.remove(curso); // Remueve el curso
                        curso.removeAlumno(this); // Establece la relación inversa
                }
        }

        @Override
        public String toString() { // Representación en cadena de la entidad
                return "Alumno{" +
                        "id=" + id +
                        ", nombre='" + nombre + '\'' +
                        ", apellido='" + apellido + '\'' +
                        ", cursos=" + cursos +
                        '}';
        }

        @Override
        public boolean equals(Object o) { // Compara objetos para igualdad
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Alumno alumno = (Alumno) o; // Castea el objeto
                return Objects.equals(id, alumno.id); // Compara IDs para igualdad
        }

        @Override
        public int hashCode() { // Genera un hash code para el objeto
                return Objects.hashCode(id); // Usa el ID para generar el hash
        }
}


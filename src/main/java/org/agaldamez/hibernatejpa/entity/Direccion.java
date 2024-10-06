package org.agaldamez.hibernatejpa.entity;

import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "direcciones") // Define el nombre de la tabla en la base de datos
public class Direccion {

        @Id // Indica que este campo es la clave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID de manera automática en la base de datos
        private Long id;

        private String calle; // Calle de la dirección
        private Integer numero; // Número de la dirección

        public Direccion() {
        }

        public Direccion(String calle, Integer numero) {
                this.calle = calle; // Asigna la calle
                this.numero = numero; // Asigna el número
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getCalle() {
                return calle;
        }

        public void setCalle(String calle) {
                this.calle = calle;
        }

        public Integer getNumero() {
                return numero;
        }

        public void setNumero(Integer numero) {
                this.numero = numero;
        }

        @Override
        public String toString() { // Representación en cadena de la entidad
                return "Direccion{" +
                        "id=" + id +
                        ", calle='" + calle + '\'' +
                        ", numero=" + numero +
                        '}';
        }
}


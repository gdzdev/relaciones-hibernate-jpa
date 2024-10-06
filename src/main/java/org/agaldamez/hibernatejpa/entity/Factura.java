package org.agaldamez.hibernatejpa.entity;

import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "facturas") // Nombre de la tabla en la base de datos
public class Factura {

        @Id // Indica que este campo es la clave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoIncremental
        private Long id;

        private String nombre; // Nombre de la factura
        private Long total; // Total de la factura

        @ManyToOne // Muchas facturas pueden tener un cliente
        @JoinColumn(name = "id_cliente") // Nombre personalizado de la FK
        private Cliente cliente;

        public Factura() {}

        public Factura(String nombre, Long total) {
                this.nombre = nombre; // Asigna el nombre de la factura
                this.total = total; // Asigna el total de la factura
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

        public Long getTotal() {
                return total;
        }

        public void setTotal(Long total) {
                this.total = total;
        }

        public Cliente getCliente() {
                return cliente;
        }

        public void setCliente(Cliente cliente) {
                this.cliente = cliente; // Asigna el cliente asociado a la factura
        }

        @Override
        public String toString() { // Representación en cadena de la entidad
                return "Factura{" +
                        "id=" + id +
                        ", nombre='" + nombre + '\'' +
                        ", total=" + total +
                        '}';
        }
}


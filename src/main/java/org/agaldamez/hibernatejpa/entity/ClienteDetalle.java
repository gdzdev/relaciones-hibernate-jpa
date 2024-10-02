package org.agaldamez.hibernatejpa.entity;

import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "clientes_detalles") // Define el nombre de la tabla en la base de datos
public class ClienteDetalle {

        @Id // Indica que este campo es la clave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID de manera automática en la base de datos
        private Long id;

        private boolean prime; // Indica si el cliente tiene una suscripción Prime

        @Column(name = "puntos_acumulados") // Mapea el campo a la columna correspondiente
        private Long puntosAcumulados; // Puntos acumulados por el cliente

        @OneToOne // Relación uno a uno con el cliente
        @JoinColumn(name = "cliente_detalle_id") // Columna que hace referencia al cliente
        private Cliente cliente; // Cliente asociado a este detalle

        public ClienteDetalle() {
        }

        public ClienteDetalle(boolean prime, Long puntosAcumulados) {
                this.prime = prime; // Asigna el estado de la suscripción Prime
                this.puntosAcumulados = puntosAcumulados; // Asigna los puntos acumulados
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public boolean isPrime() {
                return prime;
        }

        public void setPrime(boolean prime) {
                this.prime = prime;
        }

        public Long getPuntosAcumulados() {
                return puntosAcumulados;
        }

        public void setPuntosAcumulados(Long puntosAcumulados) {
                this.puntosAcumulados = puntosAcumulados;
        }

        public Cliente getCliente() {
                return cliente;
        }

        public void setCliente(Cliente cliente) {
                this.cliente = cliente; // Establece la relación inversa con el cliente
        }

        @Override
        public String toString() { // Representación en cadena de la entidad
                return "ClienteDetalle{" +
                        "id=" + id +
                        ", prime=" + prime +
                        ", puntosAcumulados=" + puntosAcumulados +
                        '}';
        }
}

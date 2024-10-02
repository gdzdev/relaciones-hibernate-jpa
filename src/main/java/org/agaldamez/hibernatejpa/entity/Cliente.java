package org.agaldamez.hibernatejpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "clientes") // Define el nombre de la tabla en la base de datos
public class Cliente {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID de manera automática en la base de datos
    private Long id;

    private String nombre; // Nombre del cliente
    private String apellido; // Apellido del cliente

    @Column(name = "forma_pago") // Mapea el campo a la columna correspondiente
    private String formaPago;

    @Column(name = "creado_en") // Mapea el campo a la columna correspondiente
    private LocalDateTime creadoEn; // Fecha de creación

    @Column(name = "editado_en") // Mapea el campo a la columna correspondiente
    private LocalDateTime editadoEn; // Fecha de última edición

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Relación uno a muchos con eliminación en cascada
    //@JoinColumn(name = "cliente_id") // Opcional: poner la FK en la tabla cliente
    @JoinTable( // Personaliza la tabla intermedia para la relación
            name = "tbl_clientes_direcciones", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_cliente"), // Columna que hace referencia al cliente
            inverseJoinColumns = @JoinColumn(name = "id_direccion"), // Columna que hace referencia a la dirección
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"}) // Restringe combinaciones únicas de direcciones
    )
    private List<Direccion> direcciones; // Lista de direcciones asociadas al cliente

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente") // Relación uno a muchos con el mapeo inverso
    private List<Factura> facturas; // Lista de facturas asociadas al cliente

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente") // Relación uno a uno con mapeo inverso
    private ClienteDetalle detalle; // Detalle del cliente

    public Cliente() {
        this.direcciones = new ArrayList<>(); // Inicializa la lista de direcciones
        this.facturas = new ArrayList<>(); // Inicializa la lista de facturas
    }

    public Cliente(String nombre, String apellido, String formaPago) {
        this(); // Llama al constructor por defecto
        this.nombre = nombre; // Asigna el nombre
        this.apellido = apellido; // Asigna el apellido
        this.formaPago = formaPago; // Asigna la forma de pago
    }

    @PrePersist // Anotación para ejecutar antes de persistir
    public void prePersist() {
        System.out.println("Inicializar un dato antes de guardar en la base de datos");
        this.creadoEn = LocalDateTime.now(); // Establece la fecha de creación
    }

    @PreUpdate // Anotación para ejecutar antes de actualizar
    public void preUpdate() {
        System.out.println("Inicializar un dato antes de actualizar en la base de datos");
        this.editadoEn = LocalDateTime.now(); // Establece la fecha de edición
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

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getEditadoEn() {
        return editadoEn;
    }

    public void setEditadoEn(LocalDateTime editadoEn) {
        this.editadoEn = editadoEn;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public ClienteDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ClienteDetalle detalle) {
        this.detalle = detalle;
    }

    public void addDetalle(ClienteDetalle detalle) {
        this.detalle = detalle; // Establece el detalle del cliente
        detalle.setCliente(this); // Establece la relación inversa
    }

    public void removeDetalle() {
        this.detalle.setCliente(null); // Desasocia el cliente del detalle
        this.detalle = null; // Establece el detalle como nulo
    }

    public void addFactura(Factura factura) {
        this.getFacturas().add(factura); // Agrega una factura a la lista
        factura.setCliente(this); // Establece la relación inversa
    }

    public void removeFactura(Factura factura) {
        this.getFacturas().remove(factura); // Remueve una factura de la lista
        factura.setCliente(null); // Desasocia el cliente de la factura
    }

    @Override
    public String toString() { // Representación en cadena de la entidad
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", creadoEn=" + creadoEn +
                ", editadoEn=" + editadoEn +
                ", direcciones=" + direcciones +
                ", facturas=" + facturas +
                '}';
    }
}

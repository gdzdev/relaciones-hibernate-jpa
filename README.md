# Relaciones en JPA y Hibernate

Este proyecto tiene como objetivo repasar y demostrar el uso de relaciones entre entidades en JPA y Hibernate. A continuación, se describen las principales relaciones, anotaciones y tipos de carga utilizados en el proyecto.

## Relaciones

1. **Uno a Uno (One-to-One)**
    - Se establece una relación donde una entidad está relacionada con exactamente una instancia de otra entidad.
    - Ejemplo: `Cliente` y `ClienteDetalle`.

2. **Uno a Muchos (One-to-Many)**
    - Una entidad puede estar relacionada con múltiples instancias de otra entidad.
    - Ejemplo: `Cliente` y `Factura`.

3. **Muchos a Muchos (Many-to-Many)**
    - Múltiples instancias de una entidad pueden estar relacionadas con múltiples instancias de otra entidad.
    - Ejemplo: `Alumno` y `Curso`.

## Anotaciones

- **@Entity**: Marca la clase como una entidad JPA.
- **@Table**: Especifica el nombre de la tabla en la base de datos.
- **@Id**: Indica el campo que es la clave primaria.
- **@GeneratedValue**: Especifica la estrategia de generación de valores para la clave primaria.
- **@Column**: Personaliza el mapeo de la columna en la base de datos.
- **@OneToOne**: Define una relación uno a uno entre dos entidades.
- **@OneToMany**: Define una relación uno a muchos.
- **@ManyToMany**: Define una relación muchos a muchos.
- **@JoinColumn**: Especifica la columna de unión entre entidades.
- **@JoinTable**: Define una tabla de unión personalizada para relaciones muchos a muchos.
- **@PrePersist**: Método que se ejecuta antes de insertar la entidad en la base de datos.
- **@PreUpdate**: Método que se ejecuta antes de actualizar la entidad en la base de datos.

## Tipos de Carga

1. **Carga Lenta (Lazy Loading)**:
    - Los datos relacionados se cargan bajo demanda, es decir, solo cuando se accede a ellos por primera vez.
    - Ejemplo: Se puede configurar una relación con `fetch = FetchType.LAZY`.

2. **Carga Eager (Eager Loading)**:
    - Todos los datos relacionados se cargan al mismo tiempo que la entidad principal.
    - Ejemplo: Se puede configurar una relación con `fetch = FetchType.EAGER`.




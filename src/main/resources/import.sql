INSERT INTO clientes (id, nombre, apellido, forma_pago, creado_en, editado_en) VALUES(1, 'Aleks', 'Fuentes', 'efectivo', NULL, NULL),(2, 'John', 'Doe', 'debito', NULL, NULL),(3, 'Lucy', 'Velilla', 'paypal', NULL, NULL),(4, 'Lu', 'Galdamez', 'debito', NULL, NULL),(5, 'Yokoo', 'Velilla', 'efectivo', NULL, NULL),(6, 'Leo', 'Velilla', 'paypal', NULL,NULL),(8, 'Salvador', 'Velilla', 'Paypal', NULL, NULL),(9, 'Lucy', 'Velilla', 'paypal', NULL, NULL);

INSERT INTO alumnos (id, nombre, apellido) VALUES (1, 'Anne', 'Melgar'), (2, 'Sofia', 'Rocco');
INSERT INTO cursos (id, titulo, profesor) VALUES (1, 'JavaScript', 'Midu'), (2, 'Arquitectura Hexagonal', 'CodeLin');
INSERT INTO tbl_alumnos_cursos (alumno_id, curso_id) VALUES (1, 1), (1, 2);

INSERT INTO direcciones (calle, numero) VALUES ('Calle Motocross', 344), ('Vaticano', 234);

// Relacion @OneToMany (un cliente tiene muchas direcciones)
INSERT INTO tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (1, 1);
INSERT INTO tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (1, 2);
INSERT INTO clientes_detalles (prime, puntos_acumulados, cliente_detalle_id) VALUES (1, 8000, 1);



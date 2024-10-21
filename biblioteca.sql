-- borra la bd si existe
DROP DATABASE IF EXISTS bd_proyecto1_lp2;
-- creamos la bd
CREATE DATABASE bd_proyecto1_lp2;
-- activamos la bd
USE db_proyecto1_lp2;

-- Insertando roles
INSERT INTO `roles` (`rol`) VALUES ('ADMIN');
INSERT INTO `roles` (`rol`) VALUES ('BIBLIOTECARIO');
INSERT INTO `roles` (`rol`) VALUES ('ESTUDIANTE');
INSERT INTO `roles` (`rol`) VALUES ('PROFESOR');

-- Insertando estados
INSERT INTO `estados` (`estado`) VALUES ('prestado');
INSERT INTO `estados` (`estado`) VALUES ('devuelto');

-- Insertando categorías
INSERT INTO `categorias` (`categoria`) VALUES ('Ciencia ficción');
INSERT INTO `categorias` (`categoria`) VALUES ('Fantasía');
INSERT INTO `categorias` (`categoria`) VALUES ('Historia');
INSERT INTO `categorias` (`categoria`) VALUES ('Tecnología');
INSERT INTO `categorias` (`categoria`) VALUES ('Arte');

-- Insertando usuarios (2 admins y 3 usuarios normales)
-- Usuarios tipo admin
INSERT INTO `usuarios` (`dni`, `nombre`, `apellido`, `email`, `username`, `psw`, `rol`)
VALUES ('12345678', 'Carlos', 'Martínez', 'admin@admin.com', 'adminUser1', 'admin123', 1); -- Rol ADMIN

INSERT INTO `usuarios` (`dni`, `nombre`, `apellido`, `email`, `username`, `psw`, `rol`)
VALUES ('23456789', 'Marta', 'Gómez', 'marta.biblio@example.com', 'biblioUser', 'biblio123', 2); -- Rol BIBLIOTECARIO

-- Usuarios tipo usuario (ESTUDIANTE, PROFESOR)
INSERT INTO `usuarios` (`dni`, `nombre`, `apellido`, `email`, `username`, `psw`, `rol`)
VALUES ('34567890', 'Ana', 'Pérez', 'ana.estudiante@example.com', 'estudianteUser', 'user123', 3); -- Rol ESTUDIANTE

INSERT INTO `usuarios` (`dni`, `nombre`, `apellido`, `email`, `username`, `psw`, `rol`)
VALUES ('45678901', 'Luis', 'Fernández', 'luis.profesor@example.com', 'profesorUser', 'user123', 4); -- Rol PROFESOR

INSERT INTO `usuarios` (`dni`, `nombre`, `apellido`, `email`, `username`, `psw`, `rol`)
VALUES ('56789012', 'Pedro', 'Rodríguez', 'pedro.estudiante@example.com', 'estudianteUser2', 'user123', 3); -- Otro ESTUDIANTE

-- Insertando libros con portadas
INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9783161484100', 'Aprendiendo JavaScript', 'Carlos Azaustre', 250, 10, 'lib1.jpg', 1);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9780743273565', 'Panteón', 'Carlos Sisí', 1178, 5, 'lib2.jpg', 2);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9780451530352', 'Java', 'Herbert Schildt', 328, 7, 'lib3.jpg', 1);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9781451673218', 'Learning Spring Boot', 'Greg L. Turnquist', 212, 8, 'lib4.jpg', 3);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9780307947927', 'Marketing Management', 'Philip Kotler', 273, 15, 'lib5.jpg', 5);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9780140177398', 'París puede esperar', 'Marisa Sicilia', 288, 9, 'lib6.jpeg', 1);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9780452284234', 'Yo antes de ti', 'Jojo Moyes', 120, 20, 'lib7.jpg', 4);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9780452277502', 'Spring Boot in Action', 'Craig Walls', 489, 12, 'lib8.jpg', 2);

INSERT INTO `libros` (`isbn`, `titulo`, `autor`, `paginas`, `cantidad`, `portada`, `categoria`)
VALUES ('9780452282501', 'El Hobbit', 'J.R.R. Tolkien', 310, 6, 'default.png', 2);


-- Insertando préstamos
INSERT INTO `prestamos` (`fecprestamo`, `fecdevolucion`, `diasretraso`, `libro`, `usuario`, `estado`)
VALUES ('2024-10-01', '2024-10-15', 0, 1, 1, 1); -- Prestamo del libro 1 por el usuario 1 (Admin), estado 'prestado'

INSERT INTO `prestamos` (`fecprestamo`, `fecdevolucion`, `diasretraso`, `libro`, `usuario`, `estado`)
VALUES ('2024-09-01', '2024-09-15', 2, 2, 2, 2); -- Prestamo del libro 2 por el usuario 2 (Bibliotecario), estado 'devuelto'

INSERT INTO `prestamos` (`fecprestamo`, `fecdevolucion`, `diasretraso`, `libro`, `usuario`, `estado`)
VALUES ('2024-10-05', '2024-10-19', 0, 3, 3, 1); -- Prestamo del libro 3 por el usuario 3 (Estudiante), estado 'prestado'

INSERT INTO `prestamos` (`fecprestamo`, `fecdevolucion`, `diasretraso`, `libro`, `usuario`, `estado`)
VALUES ('2024-09-20', '2024-10-05', 1, 4, 4, 2); -- Prestamo del libro 4 por el usuario 4 (Profesor), estado 'devuelto'

INSERT INTO `prestamos` (`fecprestamo`, `fecdevolucion`, `diasretraso`, `libro`, `usuario`, `estado`)
VALUES ('2024-10-10', '2024-10-24', 0, 5, 5, 1); -- Prestamo del libro 5 por el usuario 5 (Estudiante), estado 'prestado'






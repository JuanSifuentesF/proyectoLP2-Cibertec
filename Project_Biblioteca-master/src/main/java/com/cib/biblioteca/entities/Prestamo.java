package com.cib.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "prestamos")
@Data
@RequiredArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprestamo")
    private Integer idPrestamo;
    @Column(name = "fecprestamo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecPrestamo;
    @Column(name = "fecdevolucion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ingrese fecha de devolución")
    private Date fecDevolucion;

    @Column(name = "diasretraso")
    private int diasRetraso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro")
    @NotNull(message = "Seleccione un libro")
    private Libro libro;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    @NotNull(message = "Seleccione un usuario")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado")
    private Estado estado;
}
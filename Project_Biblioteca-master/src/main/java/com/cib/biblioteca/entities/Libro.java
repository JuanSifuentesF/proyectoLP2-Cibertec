package com.cib.biblioteca.entities;

import com.cib.biblioteca.utils.NonZero;
import com.cib.biblioteca.utils.ValidIsbn;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "libros")
@Data
@RequiredArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibro")
    private Integer idLibro;

    @ValidIsbn
    //@NotEmpty(message = "Este campo es obligatorio")
    private String isbn;

    @NotEmpty(message = "Este campo es obligatorio")
    private String titulo;

    @NotEmpty(message = "Este campo es obligatorio")
    private String autor;

    @Column(name = "paginas")
    @NonZero(message = "N° de páginas debe ser mayor a 0")
    private int paginas;

    @NonZero(message = "La cantidad debe ser mayor a 0")
    private int cantidad;
    private String portada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria")
    @NotNull(message = "Seleccione una categoría")
    private Categoria categoria;
}
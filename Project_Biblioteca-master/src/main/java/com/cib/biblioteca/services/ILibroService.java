package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroService {
    Libro save(Libro libro);

    Optional<Libro> findById(Integer id);

    void delete(Integer id);

    List<Libro> findAll();

    List<Libro> buscar(String b);
}

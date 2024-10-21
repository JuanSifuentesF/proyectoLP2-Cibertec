package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Categoria> findAll();

    Optional<Categoria> findById(Integer id);
}

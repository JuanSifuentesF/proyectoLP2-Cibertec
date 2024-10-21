package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Estado;

import java.util.List;
import java.util.Optional;

public interface IEstadoService {

    Optional<Estado> findById(Integer id);
    List<Estado> findAll();

}

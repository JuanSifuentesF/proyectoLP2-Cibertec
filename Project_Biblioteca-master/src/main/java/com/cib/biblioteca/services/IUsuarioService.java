package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> findAll();

    Optional<Usuario> findByEmail(String email);

}

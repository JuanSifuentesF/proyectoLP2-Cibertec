package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Usuario;
import com.cib.biblioteca.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final IUsuarioRepository usuarioRepos;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepos) {
        this.usuarioRepos = usuarioRepos;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepos.findAll();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepos.findByEmail(email);
    }

}

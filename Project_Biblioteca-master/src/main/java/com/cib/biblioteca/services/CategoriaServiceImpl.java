package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Categoria;
import com.cib.biblioteca.repositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
    private final ICategoriaRepository categoriaRepos;

    @Autowired
    public CategoriaServiceImpl(ICategoriaRepository categoriaRepos) {
        this.categoriaRepos = categoriaRepos;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepos.findAll();
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return categoriaRepos.findById(id);
    }
}

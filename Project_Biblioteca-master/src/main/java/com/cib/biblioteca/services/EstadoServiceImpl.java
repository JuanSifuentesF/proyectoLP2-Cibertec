package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Estado;
import com.cib.biblioteca.repositories.IEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoServiceImpl implements IEstadoService {

    private final IEstadoRepository estadoRepos;

    @Autowired
    public EstadoServiceImpl(IEstadoRepository estadoRepos) {
        this.estadoRepos = estadoRepos;
    }

    @Override
    public Optional<Estado> findById(Integer id) {
        return estadoRepos.findById(id);
    }

    @Override
    public List<Estado> findAll() {
        return estadoRepos.findAll();
    }
}

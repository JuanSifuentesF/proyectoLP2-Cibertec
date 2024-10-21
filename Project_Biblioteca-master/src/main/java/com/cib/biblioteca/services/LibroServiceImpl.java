package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Libro;
import com.cib.biblioteca.repositories.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements ILibroService {
    private final ILibroRepository libroRepos;

    @Autowired
    public LibroServiceImpl(ILibroRepository libroRepos) {
        this.libroRepos = libroRepos;
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepos.save(libro);
    }

    @Override
    public Optional<Libro> findById(Integer id) {
        return libroRepos.findById(id);
    }

    @Override
    public void delete(Integer id) {
        libroRepos.deleteById(id);
    }

    @Override
    public List<Libro> findAll() {
        return libroRepos.findAll();
    }

    @Override
    public List<Libro> buscar(String b) {
        return findAll().stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(b.toLowerCase())
                        || l.getAutor().toLowerCase().contains(b.toLowerCase())
                        || l.getCategoria().getCategoria().toLowerCase().contains(b.toLowerCase())
                        || l.getIsbn().toLowerCase().contains(b.toLowerCase()))
                .collect(Collectors.toList());
    }
}

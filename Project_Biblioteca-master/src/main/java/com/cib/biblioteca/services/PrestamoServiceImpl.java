package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Prestamo;
import com.cib.biblioteca.repositories.IPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements IPrestamoService {
    private final IPrestamoRepository prestamoRepos;

    @Autowired
    public PrestamoServiceImpl(IPrestamoRepository prestamoRepos) {
        this.prestamoRepos = prestamoRepos;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        return prestamoRepos.save(prestamo);
    }

    @Override
    public Optional<Prestamo> findById(Integer id) {
        return prestamoRepos.findById(id);
    }

    @Override
    public void delete(Integer id) {
        prestamoRepos.deleteById(id);
    }

    @Override
    public List<Prestamo> findAll() {
        return prestamoRepos.findAll();
    }

    @Override
    public List<Prestamo> buscar(String b) {
        return findAll().stream()
                .filter(p -> p.getUsuario().getNombre().toLowerCase().contains(b.toLowerCase())
                        || p.getUsuario().getApellido().toLowerCase().contains(b.toLowerCase())
                        || p.getLibro().getTitulo().toLowerCase().contains(b.toLowerCase())
                        || p.getLibro().getAutor().toLowerCase().contains(b.toLowerCase())
                        || p.getEstado().getEstado().toLowerCase().contains(b.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> findByUsuario(Integer id) {
        return prestamoRepos.findByUsuarioIdUsuario(id);
    }

    @Override
    public List<Prestamo> buscarPorUsuario(String b, Integer id) {
        return findAll().stream()
                .filter(p -> p.getUsuario().getIdUsuario().equals(id) &&
                        (p.getUsuario().getNombre().toLowerCase().contains(b.toLowerCase())
                                || p.getUsuario().getApellido().toLowerCase().contains(b.toLowerCase())
                                || p.getLibro().getTitulo().toLowerCase().contains(b.toLowerCase())
                                || p.getLibro().getAutor().toLowerCase().contains(b.toLowerCase())))
                .collect(Collectors.toList());
    }
}

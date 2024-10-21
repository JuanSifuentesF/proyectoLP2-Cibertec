package com.cib.biblioteca.services;

import com.cib.biblioteca.entities.Prestamo;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface IPrestamoService {
    Prestamo save(Prestamo prestamo);

    Optional<Prestamo> findById(Integer id);

    void delete(Integer id);

    List<Prestamo> findAll();

    List<Prestamo> buscar(String b);
    List<Prestamo> findByUsuario(Integer id);
    List<Prestamo> buscarPorUsuario(String b, Integer id);

}

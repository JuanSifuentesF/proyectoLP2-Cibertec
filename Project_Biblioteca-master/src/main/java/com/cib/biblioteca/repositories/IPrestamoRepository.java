package com.cib.biblioteca.repositories;

import com.cib.biblioteca.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByUsuarioIdUsuario(Integer id);
}

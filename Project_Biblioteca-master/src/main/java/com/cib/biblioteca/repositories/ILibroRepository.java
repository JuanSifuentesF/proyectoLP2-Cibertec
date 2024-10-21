package com.cib.biblioteca.repositories;

import com.cib.biblioteca.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Integer> {
}

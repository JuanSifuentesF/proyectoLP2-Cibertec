package com.cib.biblioteca.repositories;

import com.cib.biblioteca.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoRepository extends JpaRepository<Estado, Integer> {
}

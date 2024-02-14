package com.example.serviciopeliculas.repository;

import com.example.serviciopeliculas.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {
}

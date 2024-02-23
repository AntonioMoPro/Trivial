package com.example.springtrivial.repositorio;

import com.example.springtrivial.modelo.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PreguntasRepositorio extends JpaRepository<Preguntas, Integer> {
}

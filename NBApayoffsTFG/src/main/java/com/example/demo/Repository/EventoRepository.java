package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}

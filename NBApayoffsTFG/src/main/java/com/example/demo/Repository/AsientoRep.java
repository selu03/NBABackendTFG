package com.example.demo.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Asiento;

public interface AsientoRep  extends JpaRepository <Asiento,Long>{
	List<Asiento> findByEventoId(Long eventoId);
}

package com.example.demo.Service;

import com.example.demo.Repository.AsientoRep;
import com.example.demo.Repository.EventoRepository;
import com.example.demo.model.Asiento;
import com.example.demo.model.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {
	 @Autowired
	    private EventoRepository eventRep;

	    public List<Evento> findAllEvents() {
	        return eventRep.findAll();
	    }

	    public Evento saveEvent(Evento event) {
	        return eventRep.save(event);
	    }
	    public Evento findEventById(Long id) {
	        return eventRep.findById(id).orElse(null);
	    }
}

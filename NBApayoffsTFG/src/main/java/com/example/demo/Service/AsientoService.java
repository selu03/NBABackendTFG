package com.example.demo.Service;

import com.example.demo.Repository.AsientoRep;
import com.example.demo.model.Asiento;
import com.example.demo.model.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AsientoService {
    @Autowired
    private AsientoRep asientoRepository;
    

    public List<Asiento> findAllAsientos() {
        return asientoRepository.findAll();
    }

    public Asiento saveAsiento(Asiento asiento) {
        return asientoRepository.save(asiento);
    }

    public List<Asiento> findAsientosByEventoId(Long eventoId) {
        return asientoRepository.findByEventoId(eventoId);
    }
}


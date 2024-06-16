package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.AsientoService;
import com.example.demo.Service.EventoService;
import com.example.demo.model.Asiento;
import com.example.demo.model.Evento;


import java.util.List;

@RestController
@RequestMapping("/Eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private AsientoService asientoService;
    


    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        return ResponseEntity.ok(eventoService.findAllEvents());
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento Evento) {
        return ResponseEntity.ok(eventoService.saveEvent(Evento));
    }
   
}

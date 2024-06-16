package com.example.demo.Controller;

import com.example.demo.Service.AsientoService;
import com.example.demo.model.Asiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Asientos")
public class AsientoController {
    @Autowired
    private AsientoService asientoService;

    @GetMapping
    public ResponseEntity<List<Asiento>> getAllAsientos() {
        return ResponseEntity.ok(asientoService.findAllAsientos());
    }

    @PostMapping
    public ResponseEntity<Asiento> saveAsiento(@RequestBody Asiento asiento) {
        return ResponseEntity.ok(asientoService.saveAsiento(asiento));
    }

    @GetMapping("/Eventos/{id}")
    public ResponseEntity<List<Asiento>> getAsientosByEventoId(@PathVariable Long id) {
        List<Asiento> asientos = asientoService.findAsientosByEventoId(id);
        if (asientos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asientos);
    }
}

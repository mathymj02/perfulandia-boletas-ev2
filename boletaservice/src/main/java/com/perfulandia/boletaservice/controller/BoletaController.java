package com.perfulandia.boletaservice.controller;

import com.perfulandia.boletaservice.model.Boleta;
import com.perfulandia.boletaservice.model.DetalleBoleta;
import com.perfulandia.boletaservice.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/boletas")
@CrossOrigin(origins = "*")
public class BoletaController {

    private final BoletaService boletaService;
    private final RestTemplate restTemplate;

    @Autowired
    public BoletaController(BoletaService boletaService, RestTemplate restTemplate) {
        this.boletaService = boletaService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Boleta> crearBoleta(@RequestBody Boleta boleta) {
        Boleta nuevaBoleta = boletaService.crearBoleta(boleta);
        return ResponseEntity.ok(nuevaBoleta);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Boleta>> obtenerBoletasPorUsuario(@PathVariable Long idUsuario) {
        List<Boleta> boletas = boletaService.obtenerBoletasPorUsuario(idUsuario);
        return ResponseEntity.ok(boletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> obtenerBoletaPorId(@PathVariable Long id) {
        return boletaService.obtenerBoletaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Boleta>> obtenerTodasLasBoletas() {
        return ResponseEntity.ok(boletaService.obtenerTodas());
    }

    @GetMapping("/DetalleBoleta/{id}")
    public ResponseEntity<DetalleBoleta> obtenerDetalle(@PathVariable Long id) {
        try {
            DetalleBoleta detalle = restTemplate.getForObject("http://localhost:8083/api/productos/" + id, DetalleBoleta.class);
            return ResponseEntity.ok(detalle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}

package com.perfulandia.boletaservice.controller;

import com.perfulandia.boletaservice.hateoas.BoletaModelAssembler;
import com.perfulandia.boletaservice.model.Boleta;
import com.perfulandia.boletaservice.service.BoletaService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    private final BoletaService service;
    private final BoletaModelAssembler assembler;

    public BoletaController(BoletaService service, BoletaModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Boleta>> listar() {
        List<EntityModel<Boleta>> boletas = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(boletas,
                linkTo(methodOn(BoletaController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Boleta> buscar(@PathVariable long id) {
        Boleta boleta = service.buscar(id);
        return assembler.toModel(boleta);
    }

    @PostMapping
    public Boleta guardar(@RequestBody Boleta boleta) {
        return service.guardar(boleta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id) {
        service.eliminar(id);
    }
}



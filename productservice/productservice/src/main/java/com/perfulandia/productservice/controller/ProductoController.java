package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.model.Usuario;
import com.perfulandia.productservice.service.ProductoService;
import com.perfulandia.productservice.assembler.ProductoModelAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService servicio;
    private final ProductoModelAssembler assembler;
    private final RestTemplate restTemplate;

    public ProductoController(ProductoService servicio, ProductoModelAssembler assembler, RestTemplate restTemplate) {
        this.servicio = servicio;
        this.assembler = assembler;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public CollectionModel<EntityModel<Producto>> listar() {
        List<EntityModel<Producto>> productos = servicio.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }

    @PostMapping
    public EntityModel<Producto> guardar(@RequestBody Producto producto) {
        Producto guardado = servicio.guardar(producto);
        return assembler.toModel(guardado);
    }

    @GetMapping("/{id}")
    public EntityModel<Producto> buscar(@PathVariable long id) {
        Producto producto = servicio.bucarPorId(id);
        return assembler.toModel(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8085/api/usuarios/" + id, Usuario.class);
    }
}


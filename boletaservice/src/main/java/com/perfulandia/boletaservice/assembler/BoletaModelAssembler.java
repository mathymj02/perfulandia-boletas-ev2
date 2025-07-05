package com.perfulandia.boletaservice.hateoas;

import com.perfulandia.boletaservice.controller.BoletaController;
import com.perfulandia.boletaservice.model.Boleta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BoletaModelAssembler implements RepresentationModelAssembler<Boleta, EntityModel<Boleta>> {

    @Override
    public EntityModel<Boleta> toModel(Boleta boleta) {
        return EntityModel.of(boleta,
                linkTo(methodOn(BoletaController.class).buscar(boleta.getId())).withSelfRel(),
                linkTo(methodOn(BoletaController.class).listar()).withRel("boletas"),
                linkTo(methodOn(BoletaController.class).guardar(null)).withRel("guardar"),
                linkTo(methodOn(BoletaController.class).eliminar(boleta.getId())).withRel("eliminar"));
    }
}



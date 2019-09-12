package com.proyecto.proyectoSoft;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

public class EnsambladorRecursosMuseos implements ResourceAssembler<Museo, Resource<Museo>> {
    @Override
    public Resource<Museo> toResource(Museo museo) {
        return new Resource<>(museo,
                linkTo(methodOn(MuseoControlador.class).one(museo.getId())).withSelfRel(),
                linkTo(methodOn(MuseoControlador.class).all()).withRel("museos"));
    }
}

package com.proyecto.proyectoSoft;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class MuseoControlador {

    private final MuseoRepositorio museoRepositorio;
    private final EnsambladorRecursosMuseos ensamblador;

    MuseoControlador(MuseoRepositorio museoRepositorio,
                     EnsambladorRecursosMuseos ensamblador) {

        this.museoRepositorio = museoRepositorio;
        this.ensamblador = ensamblador;
    }
//    @GetMapping("/museos")
//    List<Museo> all(){
//        return museoRepositorio.findAll();
//    }
    @GetMapping("/museos")
    Resources<Resource<Museo>> all() {

        List<Resource<Museo>> museos = museoRepositorio.findAll().stream()
                .map(employee -> new Resource<>(employee,
                        linkTo(methodOn(MuseoControlador.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(MuseoControlador.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        return new Resources<>(museos,
                linkTo(methodOn(MuseoControlador.class).all()).withSelfRel());
    }

    @PostMapping("/museos")
    Museo nuevoMuseo(@RequestBody Museo nuevoMuseo) {
        return museoRepositorio.save(nuevoMuseo);
    }


//    @GetMapping("/museos/{id}")
//    Museo one(@PathVariable Long id) {
//
//        return museoRepositorio.findById(id)
//                .orElseThrow(() -> new MuseoNotFoundException(id));
//    }
    @GetMapping("/employees/{id}")
    Resource<Museo> one(@PathVariable Long id) {

        Museo museo = museoRepositorio.findById(id)
                .orElseThrow(() -> new MuseoNotFoundException(id));

        return ensamblador.toResource(museo);
    }

    @PutMapping("/museos/{id}")
    Museo replaceEmployee(@RequestBody Museo nuevoMuseo, @PathVariable Long id) {

        return museoRepositorio.findById(id)
                .map(museo -> {
                    museo.setDireccion(nuevoMuseo.getDireccion());
                    museo.setNombre(nuevoMuseo.getNombre());
                    return museoRepositorio.save(museo);
                })
                .orElseGet(() -> {
                    nuevoMuseo.setId(id);
                    return museoRepositorio.save(nuevoMuseo);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        museoRepositorio.deleteById(id);
    }

}

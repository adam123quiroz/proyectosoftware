package com.proyecto.proyectoSoft;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MuseoControlador {

    private final MuseoRepositorio museoRepositorio;

    public MuseoControlador(MuseoRepositorio museoRepositorio) {
        this.museoRepositorio = museoRepositorio;
    }

    @GetMapping("/museos")
    List<Museo> all(){
        return museoRepositorio.findAll();
    }
    @PostMapping("/museos")
    Museo nuevoMuseo(@RequestBody Museo nuevoMuseo) {
        return museoRepositorio.save(nuevoMuseo);
    }

    // Single item

    @GetMapping("/museos/{id}")
    Museo one(@PathVariable Long id) {

        return museoRepositorio.findById(id)
                .orElseThrow(() -> new MuseoNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
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

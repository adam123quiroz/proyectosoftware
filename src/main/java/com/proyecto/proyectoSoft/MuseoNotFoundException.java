package com.proyecto.proyectoSoft;

public class MuseoNotFoundException extends RuntimeException {
    MuseoNotFoundException(Long id){
        super("No se pudo encontrar ese Museo");
    }
}

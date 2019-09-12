package com.proyecto.proyectoSoft;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Museo {
    private @Id @GeneratedValue Long id;
    private String direccion;
    private String nombre;

    Museo(){}

    public Museo(String direccion, String nombre) {
        this.direccion = direccion;
        this.nombre = nombre;
    }
}

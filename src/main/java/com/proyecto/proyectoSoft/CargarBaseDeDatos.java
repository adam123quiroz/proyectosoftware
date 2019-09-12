package com.proyecto.proyectoSoft;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CargarBaseDeDatos {

    @Bean
    CommandLineRunner iniciarBaseDatos(MuseoRepositorio museoRepositorio){
        return args -> {
            log.info("Cargando Datos Museo" + museoRepositorio.save(new Museo(
                    "Calle Saavedra", "Historico Guerra del Pacifico")));
            log.info("Cargando Datos Museo" + museoRepositorio.save(new Museo(
                    "Avenida 16 de Julio", "Animales Prehistoricos")));
        };
    }
}

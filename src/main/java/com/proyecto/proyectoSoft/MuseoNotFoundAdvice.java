package com.proyecto.proyectoSoft;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MuseoNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MuseoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(MuseoNotFoundException ex) {
        return ex.getMessage();
    }
}

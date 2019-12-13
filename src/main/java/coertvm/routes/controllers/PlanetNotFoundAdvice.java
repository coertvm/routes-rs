package coertvm.routes.controllers;

import coertvm.routes.exceptions.PlanetNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class PlanetNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PlanetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String planetNotFoundHandler(PlanetNotFoundException ex) {
        return ex.getMessage();
    }

}
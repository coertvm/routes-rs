package coertvm.routes.controllers;

import coertvm.routes.exceptions.RouteNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class RouteNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RouteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String routeNotFoundHandler(RouteNotFoundException ex) {
        return ex.getMessage();
    }

}
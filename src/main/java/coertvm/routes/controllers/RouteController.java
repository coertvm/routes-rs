package coertvm.routes.controllers;

import coertvm.routes.domain.Route;
import coertvm.routes.exceptions.RouteNotFoundException;
import coertvm.routes.repositories.RouteRepository;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteController {

    private final RouteRepository repository;

    public RouteController(RouteRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/routes")
    List<Route> readAll() {
        return repository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/routes")
    Route create(@RequestBody Route route) {
        return repository.save(route);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/routes/{id}")
    Route readOne(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RouteNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/routes/{id}")
    Route update(@RequestBody Route route, @PathVariable Long id) {
        return repository.findById(id)
            .map(foundRoute -> {
                foundRoute.setPlanet1(route.getPlanet1());
                foundRoute.setPlanet2(route.getPlanet2());
                foundRoute.setDistance(route.getDistance());
                return repository.save(foundRoute);
            })
            .orElseGet(() -> {
                route.setId(id);
                return repository.save(route);
            });
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/routes/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
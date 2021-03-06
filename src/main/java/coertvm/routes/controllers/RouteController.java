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

    @GetMapping("/routes")
    List<Route> readAll() {
        return repository.findAll();
    }

    @PostMapping("/routes")
    Route create(@RequestBody Route route) {
        return repository.save(route);
    }

    @GetMapping("/routes/{id}")
    Route readOne(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RouteNotFoundException(id));
    }

    @PutMapping("/routes/{id}")
    Route update(@RequestBody Route route, @PathVariable Long id) {
        return repository.findById(id)
            .map(foundRoute -> {
                foundRoute.setFrom(route.getFrom());
                foundRoute.setTo(route.getTo());
                foundRoute.setDistance(route.getDistance());
                return repository.save(foundRoute);
            })
            .orElseGet(() -> {
                route.setId(id);
                return repository.save(route);
            });
    }

    @DeleteMapping("/routes/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
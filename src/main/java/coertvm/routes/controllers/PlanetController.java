package coertvm.routes.controllers;

import coertvm.routes.domain.Planet;
import coertvm.routes.exceptions.PlanetNotFoundException;
import coertvm.routes.repositories.PlanetRepository;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlanetController {

    private final PlanetRepository repository;

    public PlanetController(PlanetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/planets")
    List<Planet> readAll() {
        return repository.findAll();
    }

    @PostMapping("/planets")
    Planet create(@RequestBody Planet planet) {
        return repository.save(planet);
    }

    @GetMapping("/planets/{id}")
    Planet readOne(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new PlanetNotFoundException(id));
    }

    @PutMapping("/planets/{id}")
    Planet update(@RequestBody Planet planet, @PathVariable Long id) {
        return repository.findById(id)
            .map(foundPlanet -> {
                foundPlanet.setName(planet.getName());
                return repository.save(foundPlanet);
            })
            .orElseGet(() -> {
                planet.setId(id);
                return repository.save(planet);
            });
    }

    @DeleteMapping("/planets/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
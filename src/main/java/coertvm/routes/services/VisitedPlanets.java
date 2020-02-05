package coertvm.routes.services;

import coertvm.routes.domain.Planet;

import java.util.HashSet;
import java.util.Set;

public class VisitedPlanets {

    private Set<Planet> planets = null;

    public VisitedPlanets() {
        planets = new HashSet<>();
    }

    public void add(Planet planet) {
        planets.add(planet);
    }

    public boolean contains(Planet planet) {
        return planets.contains(planet);
    }

}
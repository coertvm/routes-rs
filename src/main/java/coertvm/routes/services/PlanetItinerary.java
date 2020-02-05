package coertvm.routes.services;

import coertvm.routes.domain.Planet;

import java.util.LinkedList;
import java.util.Queue;

public class PlanetItinerary {

    private Queue<Planet> planets = null;

    public PlanetItinerary() {
        planets = new LinkedList<>();
    }

    public void add(Planet planet) {
        planets.add(planet);
    }

    public Planet remove() {
        return planets.poll();
    }

    public boolean hasNext() {
        return !planets.isEmpty();
    }

}
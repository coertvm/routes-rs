package coertvm.routes.services;

import coertvm.routes.domain.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathSearchState implements Iterator<Planet> {

    private Planet origin = null;
    private Planet destination = null;
    private Planet current = null;
    private PlanetItinerary planetItinerary = new PlanetItinerary();
    private VisitedPlanets visitedPlanets = new VisitedPlanets();
    private RecordedRoutes recordedRoutes = new RecordedRoutes();
    private PathList paths = null;

    public ShortestPathSearchState(Planet origin, Planet destination) {
        this.origin = origin;
        planetItinerary.add(origin);
        this.destination = destination;
        paths = new PathList(origin);
    }

    public boolean hasNext() {
        return planetItinerary.hasNext();
    }

    public Planet next() {
        current = planetItinerary.remove();
        return current;
    }

    public void addToItinerary(Planet planet) {
        planetItinerary.add(planet);
    }

    public boolean isVisited() {
        return visitedPlanets.contains(current);
    }

    public void setVisited() {
        visitedPlanets.add(current);
    }

    public void record(Route step, Planet next) {
        if (!recordedRoutes.contains(step)) {
            PathIndex pathToCurrent = paths.findShortestPathTo(current);
            if (pathToCurrent.isOpen()) {
                pathToCurrent.getPath().add(step, next);
            } else {
                Path path = new Path(pathToCurrent.getFragment());
                path.add(step, next);
                paths.add(path);
            }
            recordedRoutes.add(step);
        }
    }

    public List<Route> getResults() {
        PathIndex shortestPath = paths.findShortestPathTo(destination);
        List<Route> results = new LinkedList<>();
        if (shortestPath != null) {
            for (Route result : shortestPath.getFragment().keySet()) {
                if (result != null) {
                    results.add(result);
                }
            }
        }
        return results;
    }

}
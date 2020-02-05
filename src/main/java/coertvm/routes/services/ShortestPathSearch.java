package coertvm.routes.services;

import coertvm.routes.domain.*;
import coertvm.routes.repositories.*;

import java.util.ArrayList;
import java.util.List;

public class ShortestPathSearch {

    private List<Route> allRoutes = null;
    private ShortestPathSearchState state = null;

    public ShortestPathSearch(RouteRepository routeRepository) {
        allRoutes = routeRepository.findAll();
    }

    public List<Route> search(Planet origin, Planet destination) {
        state = new ShortestPathSearchState(origin, destination);
        while (state.hasNext()) {
            Planet current = state.next();
            if (!state.isVisited()) {
                for (Route step : findSteps(current)) {
                    Planet next = notCurrent(step, current);
                    state.record(step, next);
                    if (!next.equals(destination)) {
                        state.addToItinerary(next);
                    }
                }
                state.setVisited();
            }
        }
        return state.getResults();
    }

    private List<Route> findSteps(Planet current) {
        List<Route> steps = new ArrayList<>();
        for (Route route : allRoutes) {
            if (current.equals(route.getFrom()) ||
                    current.equals(route.getTo())) {
                steps.add(route);
            }
        }
        return steps;
    }

    private Planet notCurrent(Route route, Planet current) {
        Planet other = route.getTo();
        if (other.equals(current)) {
            other = route.getFrom();
        }
        return other;
    }

}
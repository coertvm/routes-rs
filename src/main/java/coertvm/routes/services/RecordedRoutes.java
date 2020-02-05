package coertvm.routes.services;

import coertvm.routes.domain.Route;

import java.util.HashSet;
import java.util.Set;

public class RecordedRoutes {

    private Set<Route> routes = null;

    public RecordedRoutes() {
        routes = new HashSet<>();
    }

    public void add(Route route) {
        routes.add(route);
    }

    public boolean contains(Route route) {
        return routes.contains(route);
    }

}
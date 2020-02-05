package coertvm.routes.services;

import coertvm.routes.domain.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Path {

    private Map<Route, Planet> map = null;

    public Path(Planet origin) {
        map = new LinkedHashMap<>();
        map.put(null, origin);
    }

    public Path(Map<Route, Planet> fragment) {
        map = fragment;
    }

    public void add(Route route, Planet planet) {
        map.put(route, planet);
    }

    public PathIndex indexOf(Planet searchPlanet) {

        PathIndex pathIndex = null;
        Map<Route, Planet> fragment = new LinkedHashMap<>();
        long distance = 0;

        for (Map.Entry<Route, Planet> entry : map.entrySet()) {

            Route route = entry.getKey();
            Planet planet = entry.getValue();

            fragment.put(route, planet);
            distance += (route != null ? route.getDistance() : 0);

            if (planet.equals(searchPlanet)) {
                pathIndex = new PathIndex(this, fragment, distance);
                break;
            }
        }
        return pathIndex;
    }

    public int size() {
        return map.entrySet().size();
    }

}
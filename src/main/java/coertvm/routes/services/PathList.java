package coertvm.routes.services;

import coertvm.routes.domain.Planet;

import java.util.LinkedList;
import java.util.List;

public class PathList {

    private List<Path> list = null;

    public PathList(Planet origin) {
        list = new LinkedList<>();
        Path path = new Path(origin);
        list.add(path);
    }

    public void add(Path path) {
        list.add(path);
    }

    public PathIndex findShortestPathTo(Planet planet) {
        PathIndex shortestPath = null;
        for (Path path : list) {
            PathIndex index = path.indexOf(planet);
            if ((index != null && shortestPath == null) ||
                    (index != null && index.getDistance() < shortestPath.getDistance())) {
                shortestPath = index;
            }
        }
        return shortestPath;
    }

}
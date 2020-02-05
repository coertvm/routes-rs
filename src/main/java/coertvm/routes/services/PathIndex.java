package coertvm.routes.services;

import coertvm.routes.domain.*;

import java.util.Map;

public class PathIndex {

    private Path path = null;
    private Map<Route, Planet> fragment = null;
    private long distance = 0;

    public PathIndex(Path path, Map<Route, Planet> fragment, long distance) {
        this.path = path;
        this.fragment = fragment;
        this.distance = distance;
    }

    public Path getPath() {
        return path;
    }

    public Map<Route, Planet> getFragment() {
        return fragment;
    }

    public long getDistance() {
        return distance;
    }

    public boolean isOpen() {
        return (path.size() == fragment.size());
    }

}
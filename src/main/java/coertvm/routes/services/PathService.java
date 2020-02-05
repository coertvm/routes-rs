package coertvm.routes.services;

import coertvm.routes.domain.*;
import coertvm.routes.exceptions.*;
import coertvm.routes.repositories.*;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathService {

    private PlanetRepository planetRepository = null;
    private RouteRepository routeRepository = null;

    @Autowired
    public PathService(PlanetRepository planetRepository,
                       RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
    }

    public List<Route> findShortestPath(Route route) {

        long originId = route.getFrom().getId();
        Planet origin = planetRepository.findById(originId)
                .orElseThrow(() -> new PlanetNotFoundException(originId));
        long destinationId = route.getTo().getId();
        Planet destination = planetRepository.findById(destinationId)
                .orElseThrow(() -> new PlanetNotFoundException(destinationId));

        ShortestPathSearch search = new ShortestPathSearch(routeRepository);
        return search.search(origin, destination);
    }

}
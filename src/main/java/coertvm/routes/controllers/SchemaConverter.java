package coertvm.routes.controllers;

import coertvm.routes.domain.Planet;
import coertvm.routes.domain.Route;

public class SchemaConverter {

    public static Route ToRoute(coertvm.routes.schema.Route schemaRoute) {
        Long id = schemaRoute.getId();
        Planet from = ToPlanet(schemaRoute.getFrom());
        Planet to = ToPlanet(schemaRoute.getTo());
        Long distance = schemaRoute.getDistance();
        Route route = new Route(from, to, distance);
        route.setId(id);
        return route;
    }

    public static Planet ToPlanet(coertvm.routes.schema.Planet schemaPlanet) {
        Planet planet = new Planet(schemaPlanet.getName());
        planet.setId(schemaPlanet.getId());
        return planet;
    }

    public static coertvm.routes.schema.Route FromRoute(Route route) {
        coertvm.routes.schema.Route schemaRoute =
                new coertvm.routes.schema.Route();
        schemaRoute.setId(route.getId());
        schemaRoute.setFrom(FromPlanet(route.getFrom()));
        schemaRoute.setTo(FromPlanet(route.getTo()));
        schemaRoute.setDistance(route.getDistance());
        return schemaRoute;
    }

    public static coertvm.routes.schema.Planet FromPlanet(Planet planet) {
        coertvm.routes.schema.Planet schemaPlanet =
                new coertvm.routes.schema.Planet();
        schemaPlanet.setId(planet.getId());
        schemaPlanet.setName(planet.getName());
        return schemaPlanet;
    }
}
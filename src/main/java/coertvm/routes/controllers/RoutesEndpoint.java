package coertvm.routes.controllers;

import coertvm.routes.domain.Route;
import coertvm.routes.schema.GetRoutesRequest;
import coertvm.routes.schema.GetRoutesResponse;
import coertvm.routes.services.PathService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class RoutesEndpoint {

    private static final String NAMESPACE_URI = "http://coertvm/routes";

    private PathService pathService = null;

    @Autowired
    public RoutesEndpoint(PathService pathService) {
        this.pathService = pathService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRoutesRequest")
    @ResponsePayload
    public GetRoutesResponse getRoutes(@RequestPayload GetRoutesRequest request) {
        Route requestedRoute = SchemaConverter.ToRoute(request.getRoute());
        List<Route> routes = pathService.findShortestPath(requestedRoute);
        GetRoutesResponse response = new GetRoutesResponse();
        for (Route route : routes) {
            response.getRoute().add(SchemaConverter.FromRoute(route));
        }
        return response;
    }

}
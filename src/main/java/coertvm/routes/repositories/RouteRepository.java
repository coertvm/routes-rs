package coertvm.routes.repositories;

import coertvm.routes.domain.Route;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
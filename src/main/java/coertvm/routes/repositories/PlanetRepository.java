package coertvm.routes.repositories;

import coertvm.routes.domain.Planet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
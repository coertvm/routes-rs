package coertvm.routes.repositories;

import coertvm.routes.domain.Planet;
import coertvm.routes.exceptions.PlanetNotFoundException;

import java.util.List;
import java.util.Optional;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlanetRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlanetRepository repository;

    @Test
    public void testReadAll() throws Exception {

        Planet mercury = new Planet("Mercury");
        mercury = this.entityManager.persist(mercury);
        Planet mars = new Planet("Mars");
        mars = this.entityManager.persist(mars);
        Planet saturn = new Planet("Saturn");
        saturn = this.entityManager.persist(saturn);

        List<Planet> planets = this.repository.findAll();
        int numberFound = 0;
        for (Planet planet : planets) {
            if (planet.getName() == "Mercury") {
                assertThat(planet.getId()).isEqualTo(mercury.getId());
                numberFound++;
            }
            if (planet.getName() == "Mars") {
                assertThat(planet.getId()).isEqualTo(mars.getId());
                numberFound++;
            }
            if (planet.getName() == "Saturn") {
                assertThat(planet.getId()).isEqualTo(saturn.getId());
                numberFound++;
            }
        }
        assertThat(numberFound).isEqualTo(3);
    }

    @Test
    public void testCreate() throws Exception {

        Planet jupiter = new Planet("Jupiter");
        jupiter = this.repository.save(jupiter);

        Planet planet = this.entityManager.find(Planet.class, jupiter.getId());
        assertThat(planet.getId()).isEqualTo(jupiter.getId());
        assertThat(planet.getName()).isEqualTo(jupiter.getName());
    }

    @Test
    public void testReadOne() throws Exception {

        Planet venus = new Planet("Venus");
        venus = this.entityManager.persist(venus);

        Optional<Planet> optionalPlanet = this.repository.findById(venus.getId());
        if (optionalPlanet.isPresent()) {
            Planet planet = optionalPlanet.get();
            assertThat(planet.getId()).isEqualTo(venus.getId());
            assertThat(planet.getName()).isEqualTo(venus.getName());
        } else {
            throw new PlanetNotFoundException(venus.getId());
        }
    }

    @Test
    public void testUpdate() throws Exception {

        Planet uranus = new Planet("Ooranus");
        uranus = this.entityManager.persist(uranus);

        uranus.setName("Uranus");
        Planet planet = this.repository.save(uranus);
        assertThat(planet.getId()).isEqualTo(uranus.getId());
        assertThat(planet.getName()).isEqualTo(uranus.getName());
    }

    @Test
    public void testDelete() throws Exception {

        Planet pluto = new Planet("Pluto");
        pluto = this.entityManager.persist(pluto);
        Long id = pluto.getId();

        this.repository.delete(pluto);
        Optional<Planet> optionalPlanet = this.repository.findById(id);
        assertThat(optionalPlanet.isPresent()).isEqualTo(false);
    }

}
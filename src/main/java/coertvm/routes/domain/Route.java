package coertvm.routes.domain;

import javax.persistence.*;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    @ManyToOne
    private Planet planet1 = null;
    @ManyToOne
    private Planet planet2 = null;
    private Long distance = null;

    protected Route() {
    }

    public Route(Planet planet1, Planet planet2, Long distance) {
        this.planet1 = planet1;
        this.planet2 = planet2;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getPlanet1() {
        return planet1;
    }

    public void setPlanet1(Planet planet1) {
        this.planet1 = planet1;
    }

    public Planet getPlanet2() {
        return planet2;
    }

    public void setPlanet2(Planet planet2) {
        this.planet2 = planet2;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

}
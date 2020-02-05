package coertvm.routes.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    @ManyToOne
    @NotNull(message = "From is required")
    private Planet from = null;
    @ManyToOne
    @NotNull(message = "To is required")
    private Planet to = null;
    @Min(1L)
    @NotNull(message = "Distance is required")
    private Long distance = null;

    protected Route() {
    }

    public Route(Planet from, Planet to, Long distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getFrom() {
        return from;
    }

    public void setFrom(Planet from) {
        this.from = from;
    }

    public Planet getTo() {
        return to;
    }

    public void setTo(Planet to) {
        this.to = to;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) &&
                Objects.equals(from, route.from) &&
                Objects.equals(to, route.to) &&
                Objects.equals(distance, route.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, distance);
    }

}
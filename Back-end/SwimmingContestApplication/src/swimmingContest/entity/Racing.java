package swimmingContest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Racing {

    @JsonProperty("id")
    private int idracing;

    @JsonProperty("type")
    private Type type;

    @JsonProperty("distance")
    private Integer distance;

    @JsonProperty("results")
    private List<Result> results;

    @OneToMany(cascade={CascadeType.ALL})
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public enum Type {
        Front_crawl,
        Butterfly_stroke,
        Breaststroke
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idracing")
    public int getIdracing() {
        return idracing;
    }

    public void setIdracing(int idracing) {
        this.idracing = idracing;
    }



    @Basic
    @Column(name = "type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Basic
    @Column(name = "distance")
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racing racing = (Racing) o;
        return idracing == racing.idracing &&
                Objects.equals(type, racing.type) &&
                Objects.equals(distance, racing.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idracing, type, distance);
    }
}

package swimmingContest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    @JsonProperty("id")
    private int idresult;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("contestant")
    private Contestant contestant;

    @ManyToOne
    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idresult")
    public int getIdresult() {
        return idresult;
    }

    public void setIdresult(int idresult) {
        this.idresult = idresult;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return idresult == result.idresult &&
                Objects.equals(position, result.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idresult, position);
    }
}

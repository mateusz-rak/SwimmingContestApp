package swimmingContest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contest {

    @JsonProperty("idContest")
    private int idContest;

    @JsonProperty("location")
    private String location;

    @JsonProperty("date")
    private String date;

    @JsonProperty("racings")
    private List<Racing> racings;

    @OneToMany(cascade={CascadeType.ALL})
    public List<Racing> getRacings() {
        return racings;
    }

    public void setRacings(List<Racing> racings) {
        this.racings = racings;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContest")
    public int getIdContest() {
        return idContest;
    }

    public void setIdContest(int idContest) {
        this.idContest = idContest;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return idContest == contest.idContest &&
                Objects.equals(location, contest.location) &&
                Objects.equals(date, contest.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContest, location, date);
    }

    public Contest(){

    }
}

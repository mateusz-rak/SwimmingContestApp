package swimmingContest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contestant {

    @JsonProperty("id")
    private int idcontestant;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("age")
    private Integer age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontestant")
    public int getIdcontestant() {
        return idcontestant;
    }

    public void setIdcontestant(int idcontestant) {
        this.idcontestant = idcontestant;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contestant that = (Contestant) o;
        return idcontestant == that.idcontestant &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcontestant, name, surname, sex, age);
    }
}

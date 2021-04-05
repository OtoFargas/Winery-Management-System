package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.Disease;
import cz.fi.muni.pa165.enums.GrapeColor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity class Grape representing the grape plants.
 *
 * @author Lukáš Fudor
 */

@Entity
public class Grape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private GrapeColor color;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @ElementCollection
    @NotNull
    @Column(nullable = false)
    private List<Disease> diseases = new ArrayList<>();

    @OneToMany
    private List<Harvest> harvests = new ArrayList<>();

    public Grape() {}

    public Grape(Long grapeId) {
        this.id = grapeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GrapeColor getColor() {
        return color;
    }

    public void setColor(GrapeColor color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grape grape = (Grape) o;
        return name.equals(grape.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Grape{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", diseases='" + diseases + '\'' +
                ", diseases='" + harvests + '\'' +
                '}';
    }
}

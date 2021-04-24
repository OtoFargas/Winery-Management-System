package cz.fi.muni.pa165.entities;

import cz.fi.muni.pa165.enums.Disease;
import cz.fi.muni.pa165.enums.GrapeColor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GrapeColor color;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;

    @ElementCollection
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Disease> diseases = new ArrayList<>();

    @OneToMany(mappedBy = "grape", cascade = {CascadeType.ALL})
    private Set<Harvest> harvests = new HashSet<>();

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

    public Set<Harvest> getHarvests() {
        return Collections.unmodifiableSet(harvests);
    }

    public void addHarvest(Harvest harvest) {
        this.harvests.add(harvest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grape)) return false;

        Grape grape = (Grape) o;

        return getName().equals(grape.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Grape{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", diseases='" + diseases + '\'' +
                '}';
    }
}

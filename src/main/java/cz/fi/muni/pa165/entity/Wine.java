package cz.fi.muni.pa165.entity;


import cz.fi.muni.pa165.enums.Ingredient;
import cz.fi.muni.pa165.enums.Taste;
import cz.fi.muni.pa165.enums.Color;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.util.Pair;

/**
 * Entity class for wine
 *
 * @author Vladimir Visnovsky
 */
@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer stocked;

    @NotNull
    @Column(nullable = false)
    private Integer sold;

    @NotNull
    @Column(nullable = false)
    private Pair<Color, Taste> type;

    @ElementCollection
    @NotNull
    @Column(nullable = false)
    private List<Ingredient> ingredients = new ArrayList<>();

    public Wine() {}

    public Wine(Long wineId) {
        this.id = wineId;
    }

    @Id
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

    public Integer getStocked() {
        return stocked;
    }

    public void setStocked(Integer stocked) {
        this.stocked = stocked;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Pair<Color, Taste> getType() {
        return type;
    }

    public void setType(Pair<Color, Taste> type) {
        this.type = type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return name.equals(wine.name) &&
                stocked.equals(wine.stocked) &&
                sold.equals(wine.sold) &&
                type.equals(wine.type) &&
                ingredients.equals(wine.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stocked, sold, type, ingredients);
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stocked=" + stocked +
                ", sold=" + sold +
                ", type=" + type +
                ", ingredients=" + ingredients +
                '}';
    }
}

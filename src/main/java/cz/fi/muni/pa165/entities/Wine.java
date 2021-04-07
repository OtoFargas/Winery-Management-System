package cz.fi.muni.pa165.entities;


import cz.fi.muni.pa165.enums.Ingredient;
import cz.fi.muni.pa165.enums.Taste;
import cz.fi.muni.pa165.enums.WineColor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer stocked;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer sold;

    @NotNull
    @Column(nullable = false)
    private Pair<WineColor, Taste> type;

    @ElementCollection
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "wine", cascade = {CascadeType.ALL})
    private Set<Feedback> feedbacks = new HashSet<>();

    @OneToMany(mappedBy = "wine", cascade = {CascadeType.ALL})
    private Set<Harvest> harvests = new HashSet<>();

    public Wine() {}

    public Wine(Long wineId) {
        this.id = wineId;
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

    public Pair<WineColor, Taste> getType() {
        return type;
    }

    public void setType(Pair<WineColor, Taste> type) {
        this.type = type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Feedback> getFeedbacks() {
        return Collections.unmodifiableSet(feedbacks);
    }

    public void addFeedback(Feedback feedback) {
        this.feedbacks.add(feedback);
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
        if (!(o instanceof Wine)) return false;

        Wine wine = (Wine) o;

        return getName().equals(wine.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
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
                ", feedbacks=" + feedbacks +
                '}';
    }
}

package cz.fi.muni.pa165.entity;


import cz.fi.muni.pa165.enums.Ingredient;
import cz.fi.muni.pa165.enums.Taste;
import cz.fi.muni.pa165.enums.WineColor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private Pair<WineColor, Taste> type;

    @ElementCollection
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany
    private List<Feedback> feedbacks = new ArrayList<>();


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

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
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

package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * DTO class for Wine entity.
 *
 * @author Lukáš Fudor
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class WineDTO {

    private Long id;
    private String name;
    private Integer stocked;
    private Integer sold;
    private WineColor color;
    private Taste taste;
    private Integer wineYear;

    private List<Ingredient> ingredients = new ArrayList<>();
    private Set<FeedbackDTO> feedbacks = new HashSet<>();
    private Set<HarvestDTO> harvests = new HashSet<>();

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

    public WineColor getColor() {
        return this.color;
    }

    public void setColor(WineColor color) {
        this.color = color;
    }

    public Taste getTaste() {
        return this.taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<FeedbackDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<HarvestDTO> getHarvests() {
        return harvests;
    }

    public void setHarvests(Set<HarvestDTO> harvests) {
        this.harvests = harvests;
    }

    public Integer getWineYear() {
        return wineYear;
    }

    public void setWineYear(Integer wineYear) {
        this.wineYear = wineYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WineDTO)) return false;
        WineDTO wineDTO = (WineDTO) o;
        return getName().equals(wineDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "WineDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stocked=" + stocked +
                ", sold=" + sold +
                ", color=" + color +
                ", taste=" + taste +
                ", wineYear=" + wineYear +
                ", ingredients=" + ingredients +
                ", feedbacks=" + feedbacks +
                ", harvests=" + harvests +
                '}';
    }
}

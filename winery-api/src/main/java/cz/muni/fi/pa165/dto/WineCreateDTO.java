package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO class for creating Wine entity
 *
 * @author Lukáš Fudor
 */

public class WineCreateDTO {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer stocked;

    @NotNull
    @PositiveOrZero
    private Integer sold;

    @NotNull
    private WineColor color;

    @NotNull
    private Taste taste;

    @NotNull
    private List<Ingredient> ingredients = new ArrayList<>();

    private List<Long> harvestIDs;

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

    public List<Long> getHarvestIDs() {
        return harvestIDs;
    }

    public void setHarvestIDs(List<Long> harvestIDs) {
        this.harvestIDs = harvestIDs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WineCreateDTO)) return false;
        WineCreateDTO that = (WineCreateDTO) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "WineCreateDTO{" +
                "name='" + name + '\'' +
                ", stocked=" + stocked +
                ", sold=" + sold +
                ", color=" + color +
                ", taste=" + taste +
                ", ingredients=" + ingredients +
                ", harvestIDs=" + harvestIDs +
                '}';
    }
}

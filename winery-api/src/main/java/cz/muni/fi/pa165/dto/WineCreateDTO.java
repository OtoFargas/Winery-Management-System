package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import javafx.util.Pair;

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
    private Pair<WineColor, Taste> type;

    @NotNull
    private List<Ingredient> ingredients = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WineCreateDTO that = (WineCreateDTO) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "WineCreateDTO{" +
                "name='" + name + '\'' +
                ", stocked=" + stocked +
                ", sold=" + sold +
                ", type=" + type +
                ", ingredients=" + ingredients +
                '}';
    }
}

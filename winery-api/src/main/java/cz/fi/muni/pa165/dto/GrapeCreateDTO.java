package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.Disease;
import cz.fi.muni.pa165.enums.GrapeColor;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO class for creating Grape entity
 *
 * @author Lukáš Fudor
 */

public class GrapeCreateDTO {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private GrapeColor color;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    private List<Disease> diseases = new ArrayList<>();

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
        if (!(o instanceof GrapeCreateDTO)) return false;
        GrapeCreateDTO grapeCreateDTO = (GrapeCreateDTO) o;
        return getName().equals(grapeCreateDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "GrapeCreateDTO{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", quantity=" + quantity +
                ", diseases=" + diseases +
                '}';
    }
}

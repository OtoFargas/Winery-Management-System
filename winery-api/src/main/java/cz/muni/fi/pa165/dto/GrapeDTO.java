package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * DTO class for Grape entity.
 *
 * @author Lukáš Fudor
 */

public class GrapeDTO {

    private Long id;
    private String name;
    private GrapeColor color;
    private Integer quantity;
    private List<Disease> diseases = new ArrayList<>();
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

    public Set<HarvestDTO> getHarvests() {
        return harvests;
    }

    public void setHarvests(Set<HarvestDTO> harvests) {
        this.harvests = harvests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrapeDTO)) return false;
        GrapeDTO grapeDTO = (GrapeDTO) o;
        return getName().equals(grapeDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "GrapeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", quantity=" + quantity +
                ", diseases=" + diseases +
                '}';
    }
}

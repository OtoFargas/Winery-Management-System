package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Disease;

import java.util.Objects;

/**
 * DTO class for curing Grape
 *
 * @author Lukáš Fudor
 */

public class GrapeChangeDTO {

    private Long id;

    private Disease disease;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrapeChangeDTO)) return false;
        GrapeChangeDTO that = (GrapeChangeDTO) o;
        return getId().equals(that.getId()) && getDisease() == that.getDisease() && Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDisease(), getQuantity());
    }

    @Override
    public String toString() {
        return "GrapeChangeDTO{" +
                "id=" + id +
                ", disease=" + disease +
                ", quantity=" + quantity +
                '}';
    }
}

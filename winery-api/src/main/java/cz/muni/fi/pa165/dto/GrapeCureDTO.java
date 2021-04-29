package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Disease;

import java.util.Objects;

/**
 * DTO class for curing Grape
 *
 * @author Lukáš Fudor
 */

public class GrapeCureDTO {

    private Long id;

    private Disease disease;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrapeCureDTO)) return false;
        GrapeCureDTO that = (GrapeCureDTO) o;
        return getId().equals(that.getId()) && getDisease() == that.getDisease();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDisease());
    }

    @Override
    public String toString() {
        return "GrapeCureDTO{" +
                "id=" + id +
                ", disease=" + disease +
                '}';
    }
}
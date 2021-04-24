package cz.fi.muni.pa165.entities;

import cz.fi.muni.pa165.enums.Quality;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Entity class Harvest representing the yearly harvest of grapes.
 *
 * @author Lukáš Fudor
 */
@Entity
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer harvestYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Quality quality;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Wine wine;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Grape grape;

    public Harvest() {}

    public Harvest(Long harvestId) {
        this.id = harvestId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHarvestYear() {
        return harvestYear;
    }

    public void setHarvestYear(Integer harvestYear) {
        this.harvestYear = harvestYear;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public Grape getGrape() {
        return grape;
    }

    public void setGrape(Grape grape) {
        this.grape = grape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Harvest)) return false;

        Harvest harvest = (Harvest) o;

        if (!getHarvestYear().equals(harvest.getHarvestYear())) return false;
        return getGrape().equals(harvest.getGrape());
    }

    @Override
    public int hashCode() {
        int result = getHarvestYear().hashCode();
        result = 31 * result + getGrape().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Harvest{" +
                "id=" + id +
                ", harvestYear=" + harvestYear +
                ", quality=" + quality +
                ", quantity=" + quantity +
                ", wine=" + wine +
                ", grape=" + grape +
                '}';
    }
}

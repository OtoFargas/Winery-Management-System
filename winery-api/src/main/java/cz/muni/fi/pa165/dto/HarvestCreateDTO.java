package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import cz.muni.fi.pa165.enums.Quality;

/**
 * @author Vladimir Visnovsky
 */
public class HarvestCreateDTO {

    private Integer harvestYear;

    private Quality quality;

    @PositiveOrZero
    private Integer quantity;

    @NotNull
    private WineDTO wine;

    @NotNull
    private GrapeDTO grape;

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

    public WineDTO getWine() {
        return wine;
    }

    public void setWine(WineDTO wine) {
        this.wine = wine;
    }

    public GrapeDTO getGrape() {
        return grape;
    }

    public void setGrape(GrapeDTO grape) {
        this.grape = grape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HarvestDTO)) return false;

        HarvestDTO harvest = (HarvestDTO) o;

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
        return "HarvestCreateDTO{" +
                ", harvestYear=" + harvestYear +
                ", quality=" + quality +
                ", quantity=" + quantity +
                ", wineDTO=" + wine +
                ", grapeDTO=" + grape +
                '}';
    }
}

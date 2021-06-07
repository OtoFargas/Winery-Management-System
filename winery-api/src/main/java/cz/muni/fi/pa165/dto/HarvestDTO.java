package cz.muni.fi.pa165.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.muni.fi.pa165.enums.Quality;

/**
 * @author Vladimir Visnovsky
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class HarvestDTO {

    private Long id;

    private Integer harvestYear;

    private Quality quality;

    private Integer quantity;

    private WineDTO wine;

    private GrapeDTO grape;

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
        return Objects.hash(getHarvestYear(), getGrape());
    }

    @Override
    public String toString() {
        return "HarvestDTO{" +
                "id=" + id +
                ", harvestYear=" + harvestYear +
                ", quality=" + quality +
                ", quantity=" + quantity +
                '}';
    }
}

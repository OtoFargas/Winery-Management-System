package cz.muni.fi.pa165.dto;

import javax.validation.constraints.PositiveOrZero;
import cz.muni.fi.pa165.enums.Quality;
import java.util.Objects;

/**
 * @author Vladimir Visnovsky
 */
public class HarvestCreateDTO {

    private Integer harvestYear;

    private Quality quality;

    @PositiveOrZero
    private Integer quantity;

    private Long grapeId;

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

    public Long getGrapeId() {
        return grapeId;
    }

    public void setGrapeId(Long grapeId) {
        this.grapeId = grapeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HarvestCreateDTO)) return false;
        HarvestCreateDTO that = (HarvestCreateDTO) o;
        return getHarvestYear().equals(that.getHarvestYear()) && getGrapeId().equals(that.getGrapeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHarvestYear(), getGrapeId());
    }

    @Override
    public String toString() {
        return "HarvestCreateDTO{" +
                "harvestYear=" + harvestYear +
                ", quality=" + quality +
                ", quantity=" + quantity +
                ", grapeId=" + grapeId +
                '}';
    }
}

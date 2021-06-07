package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**
 * DTO class for buying Wine
 *
 * @author Oto Fargas
 */
public class WineBuyDTO {

    private Long id;

    @NotNull
    @Positive
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WineBuyDTO)) return false;
        WineBuyDTO that = (WineBuyDTO) o;
        return getId().equals(that.getId()) && getAmount().equals(that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount());
    }
}

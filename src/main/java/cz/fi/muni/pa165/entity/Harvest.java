package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.Quality;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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
    @Column(nullable = false)
    private Integer year;

    @NotNull
    @Column(nullable = false)
    private Quality quality;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    // TODO hash a equals ale potrebujeme pravdepodobne foreign key

    @Override
    public String toString() {
        return "Harvest{" +
                "id=" + id +
                ", year=" + year +
                ", quality=" + quality +
                ", quantity=" + quantity +
                '}';
    }
}

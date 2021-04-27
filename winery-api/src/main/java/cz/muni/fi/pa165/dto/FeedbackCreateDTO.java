package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO class for creating Feedback entity
 *
 * @author Oto Fargas
 */
public class FeedbackCreateDTO {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String author;

    @NotNull
    @PositiveOrZero
    private Integer rating;

    @NotEmpty
    @Size(min = 3, max = 500)
    private String content;

    @NotNull
    @PastOrPresent
    private LocalDate date;

    private WineDTO wine;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public WineDTO getWine() {
        return wine;
    }

    public void setWine(WineDTO wine) {
        this.wine = wine;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", wine=" + wine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedbackDTO)) return false;
        FeedbackDTO that = (FeedbackDTO) o;
        return getAuthor().equals(that.getAuthor())
                && getRating().equals(that.getRating())
                && getContent().equals(that.getContent())
                && getDate().equals(that.getDate())
                && Objects.equals(getWine(), that.getWine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getRating(), getContent(), getDate(), getWine());
    }
}

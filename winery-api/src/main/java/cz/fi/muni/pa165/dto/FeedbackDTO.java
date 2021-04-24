package cz.fi.muni.pa165.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO class for Feedback
 *
 * @author Oto Fargas
 */
public class FeedbackDTO {
    private Long id;
    private String author;
    private Integer rating;
    private String content;
    private LocalDate date;
    private WineDTO wine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                "id=" + id +
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
package cz.muni.fi.pa165.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class for feedback
 *
 * @author Oto Fargas
 */
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String author;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer rating;

    @NotEmpty
    @Column(nullable = false)
    private String content;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private Date date;

    @ManyToOne()
    private Wine wine;

    public Feedback() {

    }

    public Feedback(Long feedbackId) {
        this.id = feedbackId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long feedbackId) {
        this.id = feedbackId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;

        Feedback feedback = (Feedback) o;

        if (!getAuthor().equals(feedback.getAuthor())) return false;
        if (!getRating().equals(feedback.getRating())) return false;
        if (!getContent().equals(feedback.getContent())) return false;
        if (!getDate().equals(feedback.getDate())) return false;
        return getWine().equals(feedback.getWine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getRating(), getContent(), getDate(), getWine());
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", wine=" + wine +
                '}';
    }
}

package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(nullable = false)
    private String author;

    @NotNull
    @Column(nullable = false)
    private Integer rating;

    @NotNull
    @Column(nullable = false)
    private String content;

    @NotNull
    @Column(nullable = false)
    private java.util.Date date;

    public Feedback() {

    }

    public Feedback(Long feedbackId) {
        this.id = feedbackId;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Feedback feedback = (Feedback) o;
        return id.equals(feedback.id)
            && author.equals(feedback.author)
            && rating.equals(feedback.rating)
            && content.equals(feedback.content)
            && date.equals(feedback.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, rating, content, date);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
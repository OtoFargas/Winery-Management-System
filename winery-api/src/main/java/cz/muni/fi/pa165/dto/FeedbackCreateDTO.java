package cz.muni.fi.pa165.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

/**
 * DTO class for creating Feedback entity
 *
 * @author Oto Fargas
 */
public class FeedbackCreateDTO {

    @Size(min = 3, max = 50)
    private String author;

    @NotNull
    @PositiveOrZero
    private Integer rating;

    @NotEmpty
    @Size(min = 3, max = 200)
    private String content;

    @PastOrPresent
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

    private Long wineId;

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

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", wineId=" + wineId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedbackCreateDTO)) return false;
        FeedbackCreateDTO that = (FeedbackCreateDTO) o;
        return Objects.equals(getAuthor(), that.getAuthor())
                && Objects.equals(getRating(), that.getRating())
                && Objects.equals(getContent(), that.getContent())
                && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getWineId(), that.getWineId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getRating(), getContent(), getDate(), getWineId());
    }
}

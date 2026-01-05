package com.nafiu.moviereservationservice.movies.model;

import com.nafiu.moviereservationservice.reservation.model.ShowTime;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String description;

    private String posterImage;

    private Integer year;

    @ManyToOne(cascade = CascadeType.ALL)
    private Genre genre;

    @OneToMany(mappedBy = "movie")
    List<ShowTime> showTimes;


    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Movie() {
        createdAt = updatedAt = new Date();
    }
    public Movie(String title, String description, Integer year, Genre genre, String posterImage) {
        this.id = null;
        this.title = title;
        this.description = description;
        this.year = year;
        this.genre = genre;
        this.posterImage = posterImage;
        createdAt = updatedAt = new Date();
    }

    public Movie(Integer id,
                 String title,
                 String description,
                 Integer year,
                 Genre genre,
                 String posterImage,
                 Date createdAt,
                 Date updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterImage = posterImage;
        this.year = year;
        this.genre = genre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id)
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(posterImage, movie.posterImage)
                && Objects.equals(year, movie.year)
                && Objects.equals(genre, movie.genre)
                && Objects.equals(createdAt, movie.createdAt)
                && Objects.equals(updatedAt, movie.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, posterImage, year, genre, createdAt, updatedAt);
    }
}

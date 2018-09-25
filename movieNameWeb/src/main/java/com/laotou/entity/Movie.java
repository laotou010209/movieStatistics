package com.laotou.entity;



import javax.persistence.*;
import java.io.Serializable;

/**
 * 根据电影名的实体类
 */
@Entity
@Table(name="movie_name")
public class Movie implements Serializable{

    private Integer id;
    private String movieName;
    private  double ticketPrice;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public Movie setId(Integer id) {
        this.id = id;
        return this;
    }

    public Movie() {
    }

    public String getMovieName() {
        return movieName;
    }

    public Movie setMovieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Movie setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public Movie(String movieName, double ticketPrice) {
        this.movieName = movieName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}

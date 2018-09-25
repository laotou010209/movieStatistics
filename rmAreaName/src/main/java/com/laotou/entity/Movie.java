package com.laotou.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 根据地区统计电影实体类
 */
@Entity
@Table(name="movie_area")
public class Movie implements Serializable{
    private Integer id;
    private String areaName;
    private double ticketPrice;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public Movie setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public Movie setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Movie setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public Movie() {
    }

    public Movie(Integer id, String areaName, double ticketPrice) {
        this.id = id;
        this.areaName = areaName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", areaName='" + areaName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}

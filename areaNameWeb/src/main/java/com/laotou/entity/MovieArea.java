package com.laotou.entity;



import javax.persistence.*;
import java.io.Serializable;

/**
 * 根据地区名的实体类
 */
@Entity
@Table(name="movie_area")
public class MovieArea implements Serializable{

    private Integer id;
    private String areaName;
    private  double ticketPrice;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public MovieArea setId(Integer id) {
        this.id = id;
        return this;
    }

    public MovieArea() {
    }

    public String getAreaName() {
        return areaName;
    }

    public MovieArea setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public MovieArea setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public MovieArea(String areaName, double ticketPrice) {
        this.areaName = areaName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "MovieArea{" +
                "id=" + id +
                ", areaName='" + areaName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}

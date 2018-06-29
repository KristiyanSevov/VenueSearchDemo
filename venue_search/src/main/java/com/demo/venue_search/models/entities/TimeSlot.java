package com.demo.venue_search.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class TimeSlot {
    private Integer id;
    private Venue venue;
    private Date date;
    private Integer startHour;
    private Integer endHour;
    private BigDecimal price;
    private Boolean isAvailable;

    public TimeSlot() {
    }

    public TimeSlot(Venue venue, Date date, Integer startHour, Integer endHour, BigDecimal price, boolean isAvailable) {
        this.venue = venue;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(nullable = false)
    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    @Column(nullable = false)
    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false, columnDefinition = "BIT(1) DEFAULT true")
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }
}

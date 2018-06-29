package com.demo.venue_search.models.entities;

import javax.persistence.*;

@Entity
public class Venue {
    private Integer id;
    private String name;
    private Integer capacity;
    private Boolean liveStream;
    private String chairType;
    private String tableType;

    public Venue() { }

    public Venue(String name, Integer capacity, Boolean liveStream, String chairType, String tableType) {
        this.name = name;
        this.capacity = capacity;
        this.liveStream = liveStream;
        this.chairType = chairType;
        this.tableType = tableType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Column(nullable = false)
    public Boolean getLiveStream() {
        return liveStream;
    }

    public void setLiveStream(Boolean liveStream) {
        this.liveStream = liveStream;
    }

    @Column(nullable = false)
    public String getChairType() {
        return chairType;
    }

    public void setChairType(String chairType) {
        this.chairType = chairType;
    }

    @Column(nullable = false)
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}

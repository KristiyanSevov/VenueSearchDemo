package com.demo.venue_search.models.dto;

public class VenueView {
    private String name;
    private Double pricePerHour;
    private Integer capacity;
    private String chairType;
    private String tableType;
    private Boolean liveStream;

    public VenueView() {
    }

    public VenueView(String name, Double pricePerHour, Integer capacity,
                     String chairType, String tableType, Boolean liveStream) {
        this.name = name;
        this.pricePerHour = Math.round(pricePerHour * 100) / 100.0;;
        this.capacity = capacity;
        this.chairType = chairType;
        this.tableType = tableType;
        this.liveStream = liveStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = Math.round(pricePerHour * 100) / 100.0;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getChairType() {
        return chairType;
    }

    public void setChairType(String chairType) {
        this.chairType = chairType;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public Boolean getLiveStream() {
        return liveStream;
    }

    public void setLiveStream(Boolean liveStream) {
        this.liveStream = liveStream;
    }
}

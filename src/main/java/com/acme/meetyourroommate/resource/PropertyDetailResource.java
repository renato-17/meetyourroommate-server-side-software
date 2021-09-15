package com.acme.meetyourroommate.resource;

public class PropertyDetailResource {
    private Long id;
    private Integer rooms;
    private Integer squareMeters;
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(Integer squareMeters) {
        this.squareMeters = squareMeters;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

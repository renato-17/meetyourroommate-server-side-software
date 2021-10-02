package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotNull;

public class SavePropertyDetailResource {
    @NotNull
    private Integer rooms;
    @NotNull
    private Integer squareMeters;
    @NotNull
    private Float price;


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

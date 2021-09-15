package com.acme.meetyourroommate.domain.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "property_details")
public class PropertyDetail extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private Integer rooms;

    @NotNull
    private Integer squareMeters;

    @NotNull
    private Float price;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Property property;

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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
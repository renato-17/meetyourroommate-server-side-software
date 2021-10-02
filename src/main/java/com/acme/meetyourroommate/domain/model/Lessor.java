package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("lessor")
public class Lessor extends Person{
    @OneToMany(mappedBy = "lessor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Property> properties;

    @OneToMany(mappedBy = "lessor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationRequest> reservationRequests;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<ReservationRequest> getReservationRequests() {
        return reservationRequests;
    }

    public void setReservationRequests(List<ReservationRequest> reservationRequests) {
        this.reservationRequests = reservationRequests;
    }
}

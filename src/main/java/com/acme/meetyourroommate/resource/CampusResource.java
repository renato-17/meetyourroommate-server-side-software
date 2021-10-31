package com.acme.meetyourroommate.resource;

public class CampusResource {
    private Long id;
    private String name;
    private String address;
    private Long studyCenterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStudyCenterId() {
        return studyCenterId;
    }

    public void setStudyCenterId(Long studyCenterId) {
        this.studyCenterId = studyCenterId;
    }
}

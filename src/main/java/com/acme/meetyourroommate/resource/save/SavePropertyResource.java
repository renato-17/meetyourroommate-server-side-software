package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SavePropertyResource {
    @NotNull
    @NotBlank
    @Size(max = 150)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String description;

    @NotNull
    private Long lessorId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLessorId() {
        return lessorId;
    }

    public void setLessorId(Long lessorId) {
        this.lessorId = lessorId;
    }
}

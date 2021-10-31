package com.acme.meetyourroommate.resource.save;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveTaskResource {
    @NotNull
    @NotBlank
    private String description;

    private Boolean active = true;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

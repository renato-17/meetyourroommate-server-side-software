package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveAdResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private Long propertyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}

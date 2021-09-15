package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SavePropertyResourceResource {
    @NotNull
    @NotBlank
    private String link;

    @NotNull
    private Long propertyDetailId;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getPropertyDetailId() {
        return propertyDetailId;
    }

    public void setPropertyDetailId(Long propertyDetailId) {
        this.propertyDetailId = propertyDetailId;
    }
}

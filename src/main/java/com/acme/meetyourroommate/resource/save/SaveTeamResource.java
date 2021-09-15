package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveTeamResource {
    @NotNull
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

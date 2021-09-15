package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveTaskResource {
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private Long teamId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}

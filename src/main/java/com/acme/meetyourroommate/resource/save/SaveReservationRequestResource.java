package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotNull;

public class SaveReservationRequestResource {
    @NotNull
    private Long teamId;

    @NotNull
    private Long lessorId;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getLessorId() {
        return lessorId;
    }

    public void setLessorId(Long lessorId) {
        this.lessorId = lessorId;
    }
}

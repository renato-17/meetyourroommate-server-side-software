package com.acme.meetyourroommate.resource;

public class ReservationRequestResource {
    private Long id;
    private TeamResource team;
    private LessorResource lessor;
    private Integer status;
    private String statusDescription;


    public Long getId() {
        return id;
    }

    public ReservationRequestResource setId(Long id) {
        this.id = id;
        return this;
    }

    public TeamResource getTeam() {
        return team;
    }

    public ReservationRequestResource setTeam(TeamResource team) {
        this.team = team;
        return this;
    }

    public LessorResource getLessor() {
        return lessor;
    }

    public ReservationRequestResource setLessor(LessorResource lessor) {
        this.lessor = lessor;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ReservationRequestResource setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public ReservationRequestResource setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }
}

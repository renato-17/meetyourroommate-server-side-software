package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(RequestId.class)
@Table(name = "requests")
public class FriendRequest {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_send", nullable = false)
    private Student studentSend;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_received", nullable = false)
    private Student studentReceived;

    @NotNull
    private Integer status;

    @NotNull
    private String statusDescription;

    @NotNull
    private Boolean type;

    public Student getStudentSend() {
        return studentSend;
    }

    public void setStudentSend(Student studentSend) {
        this.studentSend = studentSend;
    }

    public Student getStudentReceived() {
        return studentReceived;
    }

    public void setStudentReceived(Student studentReceived) {
        this.studentReceived = studentReceived;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}

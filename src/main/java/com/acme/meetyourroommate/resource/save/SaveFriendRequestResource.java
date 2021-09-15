package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotNull;

public class SaveFriendRequestResource {
    @NotNull
    private Long studentSend;

    @NotNull
    private Long studentReceived;

    public Long getStudentSend() {
        return studentSend;
    }

    public void setStudentSend(Long studentSend) {
        this.studentSend = studentSend;
    }

    public Long getStudentReceived() {
        return studentReceived;
    }

    public void setStudentReceived(Long studentReceived) {
        this.studentReceived = studentReceived;
    }
}

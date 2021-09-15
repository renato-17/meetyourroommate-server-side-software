package com.acme.meetyourroommate.resource;

public class FriendRequestResource {
    private StudentResource studentSend;
    private StudentResource studentReceived;
    private Integer status;
    private String statusDescription;

    public StudentResource getStudentSend() {
        return studentSend;
    }

    public void setStudentSend(StudentResource studentSend) {
        this.studentSend = studentSend;
    }

    public StudentResource getStudentReceived() {
        return studentReceived;
    }

    public void setStudentReceived(StudentResource studentReceived) {
        this.studentReceived = studentReceived;
    }
}

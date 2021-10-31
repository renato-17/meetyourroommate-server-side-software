package com.acme.meetyourroommate.resource;

public class FriendRequestResource {
    private Long studentSendId;
    private Long studentReceivedId;
    private Integer status;
    private String statusDescription;

    public Long getStudentSendId() {
        return studentSendId;
    }

    public void setStudentSendId(Long studentSendId) {
        this.studentSendId = studentSendId;
    }

    public Long getStudentReceivedId() {
        return studentReceivedId;
    }

    public void setStudentReceivedId(Long studentReceivedId) {
        this.studentReceivedId = studentReceivedId;
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
}

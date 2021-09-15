package com.acme.meetyourroommate.resource;

public class AdResource {
    private Long id;
    private String title;
    private Integer viewsNumber;
    private Integer likesNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(Integer viewsNumber) {
        this.viewsNumber = viewsNumber;
    }

    public Integer getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }
}

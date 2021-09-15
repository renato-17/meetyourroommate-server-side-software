package com.acme.meetyourroommate.resource.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCommentResource {
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String text;

    @NotNull
    private Long adId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
}

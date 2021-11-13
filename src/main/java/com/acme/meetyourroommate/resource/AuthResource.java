package com.acme.meetyourroommate.resource;

public class AuthResource {
    private Long id;
    private String discriminator;

    public AuthResource(Long id, String discriminator) {
        this.id = id;
        this.discriminator = discriminator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

}

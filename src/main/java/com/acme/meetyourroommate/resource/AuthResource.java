package com.acme.meetyourroommate.resource;

public class AuthResource {
    private Long id;
    private String discriminator;
    private String token;

    public AuthResource(Long id, String discriminator, String token) {
        this.id = id;
        this.discriminator = discriminator;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

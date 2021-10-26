package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.resource.AuthResource;

public interface PersonService {
    AuthResource authentication(String mail, String password);
}

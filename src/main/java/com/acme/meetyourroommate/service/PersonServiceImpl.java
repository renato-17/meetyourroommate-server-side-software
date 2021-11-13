package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Person;
import com.acme.meetyourroommate.domain.repository.PersonRepository;
import com.acme.meetyourroommate.domain.service.PersonService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.resource.AuthResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @Override
    public AuthResource authentication(String mail, String password) {
        Person person = personRepository.findByMail(mail)
                .orElseThrow(()-> new ResourceNotFoundException("Person","Mail",mail));

        if(!person.getPassword().equals(password)){
            return null;
        }
        return new AuthResource(person.getId(),person.getPersonType());
    }
}

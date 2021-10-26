package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Person;
import com.acme.meetyourroommate.domain.repository.PersonRepository;
import com.acme.meetyourroommate.domain.service.PersonService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.resource.AuthResource;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        return new AuthResource(person.getId(),person.getPersonType(),getJWTToken(person.getDni()));
    }

    private String getJWTToken(String dni) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(dni)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}

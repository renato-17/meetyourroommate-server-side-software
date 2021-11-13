package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.service.LessorService;
import com.acme.meetyourroommate.domain.service.PersonService;
import com.acme.meetyourroommate.domain.service.StudentService;
import com.acme.meetyourroommate.resource.AuthResource;
import com.acme.meetyourroommate.resource.LessorResource;
import com.acme.meetyourroommate.resource.StudentResource;
import com.acme.meetyourroommate.resource.save.SaveLessorResource;
import com.acme.meetyourroommate.resource.save.SaveStudentResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PersonService personService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LessorService lessorService;

    @Operation(summary = "User Authentication", description = "Authenticate user using credentials", tags = {"users"})
    @PostMapping("/users")
    public AuthResource authenticate(@RequestParam("mail") String mail,
                                     @RequestParam("pwd") String password) {
        return personService.authentication(mail, password);
    }

}

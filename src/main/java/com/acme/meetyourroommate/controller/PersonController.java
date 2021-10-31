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
@RequestMapping("/users")
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
    @PostMapping("/authentication")
    public AuthResource authenticate(@RequestParam("m") String mail,
                                     @RequestParam("p") String password) {
        return personService.authentication(mail, password);
    }

    @Operation(summary = "Create Student", description = "Create a new Student", tags = {"users"})
    @PostMapping("/students")
    public StudentResource createStudent(@Valid @RequestBody SaveStudentResource resource,
                                         @RequestParam("campus") Long campusId) {
        Student student = mapper.map(resource, Student.class);
        return mapper.map(studentService.createStudent(campusId, student), StudentResource.class);
    }

    @Operation(summary = "Create Lessor", description = "Create a new lessor", tags = {"users"})
    @PostMapping("/lessors")
    public LessorResource createLessor(@Valid @RequestBody SaveLessorResource resource){
        Lessor lessor = mapper.map(resource, Lessor.class);
        return mapper.map(lessorService.createLessor(lessor),LessorResource.class);
    }
}

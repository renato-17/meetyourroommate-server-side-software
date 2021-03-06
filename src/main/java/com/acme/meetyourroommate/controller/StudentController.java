package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.service.StudentService;
import com.acme.meetyourroommate.resource.StudentResource;
import com.acme.meetyourroommate.resource.save.SaveStudentResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Get All students", description = "Get all students", tags = {"students"})
    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable){
        Page<Student> studentPage = studentService.getAllStudents(pageable);

        List<StudentResource> resources = studentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Student By Id", description = "Get Student By Id", tags = {"students"})
    @GetMapping("/students/{studentId}")
    public StudentResource getStudentById(@PathVariable Long studentId){
        return convertToResource(studentService.getStudentById(studentId));
    }
//    @Operation(summary = "Get Student By Dni", description = "Get Student By Dni", tags = {"students"})
//    @GetMapping("/students/{studentDni}")
//    public StudentResource getStudentByDni(@PathVariable String studentDni){
//        return convertToResource(studentService.getStudentByDni(studentDni));
//    }

    @Operation(summary = "Update Student", description = "Update Student", tags = {"students"})
    @PutMapping("/students/{studentId}")
    public StudentResource updateStudent(
            @PathVariable Long studentId,
            @RequestBody @Valid SaveStudentResource resource){
        Student student = convertToEntity(resource);
        return convertToResource(studentService.updateStudent(student,studentId));
    }

    @Operation(summary = "Join a Team", description = "Join a Team", tags = {"students"})
    @PatchMapping("team/{teamId}/students/{studentId}")
    public StudentResource joinATeam(
            @PathVariable Long studentId,
            @PathVariable Long teamId){
        return convertToResource(studentService.joinATeam(studentId,teamId));
    }

    @Operation(summary = "Disjoint a Team", description = "Disjoint a Team", tags = {"students"})
    @PatchMapping("/students/{studentId}")
    public StudentResource disjointATeam(
            @PathVariable Long studentId){
        return convertToResource(studentService.disjointATeam(studentId));
    }

    @Operation(summary = "Delete Student", description = "Delete Student", tags = {"students"})
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }

    private  Student convertToEntity(SaveStudentResource resource){return mapper.map(resource,Student.class);}
    private  StudentResource convertToResource(Student entity){return mapper.map(entity,StudentResource.class);}
}

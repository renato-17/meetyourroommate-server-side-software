package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.repository.CampusRepository;
import com.acme.meetyourroommate.domain.repository.StudentRepository;
import com.acme.meetyourroommate.domain.service.StudentService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CampusRepository campusRepository;

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));

    }

    @Override
    public Student getStudentByDni(String studentDni) {
        return studentRepository.findByDni(studentDni)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Dni",studentDni));
    }

    @Override
    public Student createStudent(Long campusId, Student student) {
        return campusRepository.findById(campusId).map(campus ->{
            student.setPremium(false);
            student.setCampus(campus);
            return studentRepository.save(student);
        }).orElseThrow(()->new ResourceNotFoundException("Campus","Id",campusId));
    }

    @Override
    public Student updateStudent(Student studentRequest, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setAddress(studentRequest.getAddress());
        student.setDescription(studentRequest.getDescription());
        student.setHobbies(studentRequest.getHobbies());
        student.setSmoker(studentRequest.getSmoker());
        student.setSearching(studentRequest.getSearching());
        student.setBirthdate(studentRequest.getBirthdate());
        student.setPremium(studentRequest.getPremium());
        student.setGender(studentRequest.getGender());
        student.setDni(studentRequest.getDni());

        return studentRepository.save(student);
    }

    @Override
    public ResponseEntity<?> deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }
}

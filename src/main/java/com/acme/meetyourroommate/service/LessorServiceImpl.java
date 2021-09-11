package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.repository.LessorRepository;
import com.acme.meetyourroommate.domain.service.LessorService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LessorServiceImpl implements LessorService {

    @Autowired
    private LessorRepository lessorRepository;

    @Override
    public Page<Lessor> getAllLessors(Pageable pageable) {
        return lessorRepository.findAll(pageable);
    }

    @Override
    public Lessor getLessorById(Long lessorId) {
        return lessorRepository.findById(lessorId)
                .orElseThrow(()->new ResourceNotFoundException("Lessor", "Id", lessorId));
    }

    @Override
    public Lessor createLessor(Lessor lessor) {
        lessor.setPremium(false);
        return lessorRepository.save(lessor);
    }

    @Override
    public Lessor updateLessor(Lessor lessorRequest, Long lessorId) {
        Lessor lessor = lessorRepository.findById(lessorId)
                .orElseThrow(()->new ResourceNotFoundException("Lessor", "Id", lessorId));

        lessor.setFirstName(lessorRequest.getFirstName());
        lessor.setLastName(lessorRequest.getLastName());
        lessor.setPhoneNumber(lessorRequest.getPhoneNumber());
        lessor.setAddress(lessorRequest.getAddress());
        lessor.setBirthdate(lessorRequest.getBirthdate());
        lessor.setPremium(lessorRequest.getPremium());
        lessor.setGender(lessorRequest.getGender());
        lessor.setDni(lessorRequest.getDni());

        return lessorRepository.save(lessor);
    }

    @Override
    public ResponseEntity<?> deleteLessor(Long lessorId) {
        Lessor lessor = lessorRepository.findById(lessorId)
                .orElseThrow(()->new ResourceNotFoundException("Lessor", "Id", lessorId));
        lessorRepository.delete(lessor);
        return ResponseEntity.ok().build();
    }
}

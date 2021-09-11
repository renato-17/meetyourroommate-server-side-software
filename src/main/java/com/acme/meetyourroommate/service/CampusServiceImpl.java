package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.repository.CampusRepository;
import com.acme.meetyourroommate.domain.repository.StudyCenterRepository;
import com.acme.meetyourroommate.domain.service.CampusService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CampusServiceImpl implements CampusService {
    @Autowired
    private CampusRepository campusRepository;
    @Autowired
    private StudyCenterRepository studyCenterRepository;

    @Override
    public Page<Campus> getAllCampus(Pageable pageable) {
        return campusRepository.findAll(pageable);
    }

    @Override
    public Campus getCampusById(Long campusId) {
        return campusRepository.findById(campusId)
                .orElseThrow(()-> new ResourceNotFoundException("Campus","Id",campusId));
    }

    @Override
    public Page<Campus> getAllCampusesByStudyCenterId(Long studyCenterId, Pageable pageable) {
        return campusRepository.findByStudyCenterId(studyCenterId,pageable);
    }

    @Override
    public Campus getCampusByIdAndStudyCenterId(Long studyCenterId, Long campusId) {
        return campusRepository.findByIdAndStudyCenterId(campusId,studyCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("Campus or Study Center not found"));
    }

    @Override
    public Campus createCampus(Long studyCenterId, Campus campus) {
        return studyCenterRepository.findById(studyCenterId).map(studyCenter -> {
            campus.setStudyCenter(studyCenter);
            return campusRepository.save(campus);
        }).orElseThrow(()->new ResourceNotFoundException("Study Center","Id",studyCenterId));
    }

    @Override
    public Campus updateCampus(Long campusId, Campus campusRequest) {
        Campus campus = campusRepository.findById(campusId)
                .orElseThrow(()-> new ResourceNotFoundException("Campus","Id",campusId));

        campus.setAddress(campusRequest.getAddress());
        campus.setName(campusRequest.getName());

        return campusRepository.save(campus);
    }

    @Override
    public ResponseEntity<?> deleteCampus(Long campusId) {
        Campus campus = campusRepository.findById(campusId)
                .orElseThrow(()->new ResourceNotFoundException("Campus", "Id", campusId));
        campusRepository.delete(campus);
        return ResponseEntity.ok().build();
    }
}

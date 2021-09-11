package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Campus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CampusService {
    Page<Campus> getAllCampus(Pageable pageable);
    Campus getCampusById(Long campusId);

    Page<Campus> getAllCampusesByStudyCenterId(Long studyCenterId, Pageable pageable);
    Campus getCampusByIdAndStudyCenterId(Long studyCenterId,Long campusId);

    Campus createCampus(Long studyCenterId,Campus campus);
    Campus updateCampus(Long campusId, Campus campusRequest);
    ResponseEntity<?> deleteCampus(Long campusId);
}

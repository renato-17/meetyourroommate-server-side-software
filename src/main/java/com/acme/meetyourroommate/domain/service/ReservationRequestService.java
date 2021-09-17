package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.ReservationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReservationRequestService {
    Page<ReservationRequest> findAllByTeamId(Long teamId, Pageable pageable);

    Page<ReservationRequest> findAllReservationRequests(Pageable pageable);

    ReservationRequest findByTeamIdAndLessorId(Long teamId, Long lessorId);

    ReservationRequest createReservationRequest(Long teamId, Long lessorId, ReservationRequest reservationRequest);
    ReservationRequest updateReservationRequest(Long teamId, Long lessorId, ReservationRequest reservationRequest);
    ResponseEntity<?> deleteReservationRequest(Long teamId, Long lessorId);
}

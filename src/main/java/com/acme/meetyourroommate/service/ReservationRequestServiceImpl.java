package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.model.ReservationRequest;
import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.domain.repository.LessorRepository;
import com.acme.meetyourroommate.domain.repository.ReservationRequestRepository;
import com.acme.meetyourroommate.domain.repository.TeamRepository;
import com.acme.meetyourroommate.domain.service.ReservationRequestService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReservationRequestServiceImpl implements ReservationRequestService {

    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LessorRepository lessorRepository;

    @Override
    public Page<ReservationRequest> findAllByTeamId(Long teamId, Pageable pageable) {
        return teamRepository.findById(teamId).map(
                team -> reservationRequestRepository.findByTeam(team, pageable)
        ).orElseThrow(() -> new ResourceNotFoundException("Team", "Id", teamId));
    }

    @Override
    public Page<ReservationRequest> findAllReservationRequests(Pageable pageable) {
        return reservationRequestRepository.findAll(pageable);
    }

    @Override
    public ReservationRequest findByTeamIdAndLessorId(Long teamId, Long lessorId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "Id", teamId));
        Lessor lessor = lessorRepository.findById(lessorId)
                .orElseThrow(()-> new ResourceNotFoundException("Lessor", "Id", lessorId));
        return reservationRequestRepository.findByTeamAndLessor(team, lessor)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation request for the Team not found"));
    }

    @Override
    public ReservationRequest createReservationRequest(Long teamId, Long lessorId) {
        ReservationRequest reservationRequest = new ReservationRequest();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "Id", teamId));
        Lessor lessor = lessorRepository.findById(lessorId)
                .orElseThrow(()-> new ResourceNotFoundException("Lessor", "Id", lessorId));

        reservationRequest.setTeam(team);
        reservationRequest.setLessor(lessor);
        reservationRequest.setStatus(3);
        reservationRequest.setStatusDescription(setDescriptionStatus(3));

        return reservationRequestRepository.save(reservationRequest);
    }

    @Override
    public ReservationRequest updateReservationRequest(Long teamId, Long lessorId, ReservationRequest newReservationRequest) {
        ReservationRequest reservationRequest = findByTeamIdAndLessorId(teamId, lessorId);
        reservationRequest.setStatus(newReservationRequest.getStatus());
        reservationRequest.setStatusDescription(setDescriptionStatus(reservationRequest.getStatus()));
        return reservationRequestRepository.save(reservationRequest);
        // Teniendo en cuenta esto, deberia haber otro resource en el cual solo se mande el cambio de status
    }

    @Override
    public ResponseEntity<?> deleteReservationRequest(Long teamId, Long lessorId) {
        ReservationRequest reservationRequest = findByTeamIdAndLessorId(teamId, lessorId);
        reservationRequestRepository.delete(reservationRequest);
        return ResponseEntity.ok().build();
    }

    private String setDescriptionStatus(Integer status){
        return switch (status) {
            case 1 -> "Accepted";
            case 2 -> "Decline";
            case 3 -> "Pending";
            default -> "Incorrect status";
        };
    }
}

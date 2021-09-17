package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.model.ReservationRequest;
import com.acme.meetyourroommate.domain.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    Page<ReservationRequest> findByTeam(Team team, Pageable pageable);
    Page<ReservationRequest> findByLessor(Lessor lessor, Pageable pageable);

    Optional<ReservationRequest> findByTeamAndLessor(Team team, Lessor lessor);
}

package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

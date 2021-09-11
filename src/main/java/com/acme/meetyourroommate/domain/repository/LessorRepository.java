package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessorRepository extends JpaRepository<Lessor,Long> {
}

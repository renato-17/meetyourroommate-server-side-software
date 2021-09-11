package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad,Long> {
    Optional<Ad> findByPropertyId(Long propertyId);
    Optional<Ad> findByTitle(String title);
}

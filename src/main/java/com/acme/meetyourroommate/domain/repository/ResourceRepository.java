package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findByPropertyDetailId(Long propertyDetailId, Pageable pageable);
}

package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ResourceService {
    Page<Resource> findAllResources(Pageable pageable);
    Page<Resource> findAllResourcesByPropertyDetailId(Long propertyDetailId, Pageable pageable);

    Resource findByResourceId(Long resourceId);
    Resource createResource(Long propertyDetailId, Resource resource);
    Resource updateResource(Long resourceId, Resource resourceRequest);
    ResponseEntity<?> deleteResource(Long resourceId);
}

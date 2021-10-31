package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.PropertyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyResourceService {
    Page<PropertyResource> getAllPropertyResources(Pageable pageable);
    Page<PropertyResource> getAllPropertyResourcesByPropertyId(Long propertyId, Pageable pageable);

    PropertyResource getPropertyResourceById(Long resourceId);
    PropertyResource createPropertyResource(Long propertyDetailId, PropertyResource propertyResource);
    PropertyResource updatePropertyResource(Long resourceId, PropertyResource propertyResourceRequest);
    ResponseEntity<?> deletePropertyResource(Long resourceId);
}

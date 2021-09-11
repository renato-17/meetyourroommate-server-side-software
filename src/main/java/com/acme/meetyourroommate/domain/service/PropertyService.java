package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyService {
    Page<Property> getAllProperties(Pageable pageable);
    Property getPropertyById(Long propertyId);
    
    Page<Property> getAllPropertiesByLessorId(Long lessorId, Pageable pageable);
    Property getPropertyByIdAndLessorId(Long lessorId,Long propertyId);

    Property createProperty(Long lessorId,Property property);
    Property updateProperty(Long propertyId, Property propertyRequest);
    ResponseEntity<?> deleteProperty(Long propertyId);
}

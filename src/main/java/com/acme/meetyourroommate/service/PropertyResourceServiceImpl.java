package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.PropertyResource;
import com.acme.meetyourroommate.domain.repository.PropertyDetailRepository;
import com.acme.meetyourroommate.domain.repository.PropertyResourceRepository;
import com.acme.meetyourroommate.domain.service.PropertyResourceService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyResourceServiceImpl implements PropertyResourceService {
    @Autowired
    private PropertyResourceRepository propertyResourceRepository;
    @Autowired
    private PropertyDetailRepository propertyDetailRepository;

    @Override
    public Page<PropertyResource> getAllPropertyResources(Pageable pageable) {
        return propertyResourceRepository.findAll(pageable);
    }

    @Override
    public Page<PropertyResource> getAllPropertyResourcesByPropertyDetailId(Long propertyDetailId, Pageable pageable) {
        return propertyResourceRepository.findByPropertyDetailId(propertyDetailId,pageable);
    }

    @Override
    public PropertyResource getPropertyResourceById(Long resourceId) {
        return propertyResourceRepository.findById(resourceId)
                .orElseThrow(()->new ResourceNotFoundException("Resource","Id",resourceId));
    }

    @Override
    public PropertyResource createPropertyResource(Long propertyDetailId, PropertyResource propertyResource) {
        return propertyDetailRepository.findById(propertyDetailId).map(propertyDetail -> {
            propertyResource.setPropertyDetail(propertyDetail);
            return propertyResourceRepository.save(propertyResource);
        }).orElseThrow(()->new ResourceNotFoundException("Property Detail","Id",propertyDetailId));
    }

    @Override
    public PropertyResource updatePropertyResource(Long resourceId, PropertyResource propertyResourceRequest) {
        PropertyResource propertyResource = propertyResourceRepository.findById(resourceId)
                .orElseThrow(()->new ResourceNotFoundException("Resource","Id",resourceId));
        propertyResource.setLink(propertyResourceRequest.getLink());
        return propertyResourceRepository.save(propertyResource);
    }

    @Override
    public ResponseEntity<?> deletePropertyResource(Long resourceId) {
        PropertyResource propertyResource = propertyResourceRepository.findById(resourceId)
                .orElseThrow(()->new ResourceNotFoundException("Resource","Id",resourceId));
        propertyResourceRepository.delete(propertyResource);
        return ResponseEntity.ok().build();
    }
}

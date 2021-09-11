package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Resource;
import com.acme.meetyourroommate.domain.repository.PropertyDetailRepository;
import com.acme.meetyourroommate.domain.repository.ResourceRepository;
import com.acme.meetyourroommate.domain.service.PropertyDetailService;
import com.acme.meetyourroommate.domain.service.ResourceService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private PropertyDetailRepository propertyDetailRepository;

    @Override
    public Page<Resource> findAllResources(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    @Override
    public Page<Resource> findAllResourcesByPropertyDetailId(Long propertyDetailId, Pageable pageable) {
        return resourceRepository.findByPropertyDetailId(propertyDetailId,pageable);
    }

    @Override
    public Resource findByResourceId(Long resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(()->new ResourceNotFoundException("Resource","Id",resourceId));
    }

    @Override
    public Resource createResource(Long propertyDetailId, Resource resource) {
        return propertyDetailRepository.findById(propertyDetailId).map(propertyDetail -> {
            resource.setPropertyDetail(propertyDetail);
            return resourceRepository.save(resource);
        }).orElseThrow(()->new ResourceNotFoundException("Property Detail","Id",propertyDetailId));
    }

    @Override
    public Resource updateResource(Long resourceId, Resource resourceRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteResource(Long resourceId) {
        return null;
    }
}

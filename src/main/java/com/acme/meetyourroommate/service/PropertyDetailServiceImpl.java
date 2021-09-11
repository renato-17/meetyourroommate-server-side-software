package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import com.acme.meetyourroommate.domain.repository.PropertyDetailRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.PropertyDetailService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyDetailServiceImpl implements PropertyDetailService {
    @Autowired
    private PropertyDetailRepository propertyDetailRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Page<PropertyDetail> getAllPropertyDetails(Pageable pageable) {
        return propertyDetailRepository.findAll(pageable);
    }

    @Override
    public PropertyDetail getPropertyDetailById(Long propertyDetailId) {
        return propertyDetailRepository.findById(propertyDetailId)
                .orElseThrow(()->new ResourceNotFoundException("Property Detail","Id",propertyDetailId));
    }

    @Override
    public PropertyDetail getPropertyDetailByPropertyId(Long propertyId) {
        return propertyDetailRepository.findByPropertyId(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property","Id",propertyId));
    }

    @Override
    public PropertyDetail createPropertyDetail(Long propertyId, PropertyDetail propertyDetail) {
        return propertyRepository.findById(propertyId).map(property->{
            propertyDetail.setProperty(property);
            return propertyDetailRepository.save(propertyDetail);
        }).orElseThrow(()->new ResourceNotFoundException("Property","Id",propertyId));
    }

    @Override
    public PropertyDetail updatePropertyDetail(Long propertyDetailId, PropertyDetail propertyDetailRequest) {
        PropertyDetail propertyDetail = propertyDetailRepository.findById(propertyDetailId)
                .orElseThrow(()->new ResourceNotFoundException("Property Detail","Id",propertyDetailId));
        propertyDetail.setPrice(propertyDetailRequest.getPrice());
        propertyDetail.setRooms(propertyDetailRequest.getRooms());
        propertyDetail.setSquareMeters(propertyDetailRequest.getSquareMeters());

        return propertyDetailRepository.save(propertyDetail);
    }

    @Override
    public ResponseEntity<?> deleteProperty(Long propertyDetailId) {
        PropertyDetail propertyDetail = propertyDetailRepository.findById(propertyDetailId)
                .orElseThrow(()->new ResourceNotFoundException("Property Detail","Id",propertyDetailId));
        propertyDetailRepository.delete(propertyDetail);
        return ResponseEntity.ok().build();
    }
}

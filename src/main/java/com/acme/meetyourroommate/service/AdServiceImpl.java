package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.repository.AdRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.AdService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Page<Ad> getAllAds(Pageable pageable) {
        return adRepository.findAll(pageable);
    }

    @Override
    public Ad getAdById(Long adId) {
        return adRepository.findById(adId)
                .orElseThrow(()->new ResourceNotFoundException("Ad","Id",adId));
    }

    @Override
    public Ad getAdByPropertyId(Long propertyId) {
        return adRepository.findByPropertyId(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property","Id",propertyId));
    }

    @Override
    public Ad getAdByTitle(String title) {
        return adRepository.findByTitle(title)
                .orElseThrow(()->new ResourceNotFoundException("Property","Title",title));
    }

    @Override
    public Ad createAd(Long propertyId, Ad ad) {
        return propertyRepository.findById(propertyId).map(property -> {
            ad.setProperty(property);
            return adRepository.save(ad);
        }).orElseThrow(()->new ResourceNotFoundException("Property","Id",propertyId));
    }

    @Override
    public Ad updateAd(Long adId, Ad adRequest) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(()->new ResourceNotFoundException("Ad","Id",adId));
        ad.setTitle(adRequest.getTitle());
        ad.setLikesNumber(adRequest.getLikesNumber());
        ad.setViewsNumber(adRequest.getViewsNumber());
        return adRepository.save(ad);
    }

    @Override
    public ResponseEntity<?> deleteProperty(Long adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(()->new ResourceNotFoundException("Ad","Id",adId));
        adRepository.delete(ad);
        return ResponseEntity.ok().build();
    }
}

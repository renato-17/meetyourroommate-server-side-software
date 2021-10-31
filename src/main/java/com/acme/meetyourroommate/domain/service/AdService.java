package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdService {
    Page<Ad> getAllAds(Pageable pageable);
    Ad getAdById(Long adId);

    Ad getAdByPropertyId(Long propertyId);
    Ad getAdByTitle(String title);

    Ad createAd(Long propertyId,Ad ad);
    Ad updateAd(Long adId, Ad adRequest);
    ResponseEntity<?> deleteAd(Long adId);
}

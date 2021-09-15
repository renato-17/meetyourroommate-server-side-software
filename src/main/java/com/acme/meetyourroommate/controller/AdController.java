package com.acme.meetyourroommate.controller;


import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.service.AdService;
import com.acme.meetyourroommate.resource.AdResource;
import com.acme.meetyourroommate.resource.save.SaveAdResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AdService adService;

    @Operation(summary = "Get All Ads", description = "Get all ads", tags = {"ads"})
    @GetMapping("/ads")
    public Page<AdResource> getAllAds(Pageable pageable){
        Page<Ad> adPage = adService.getAllAds(pageable);

        List<AdResource> resources = adPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Ad By Id", description = "Get Ad By Id", tags = {"ads"})
    @GetMapping("/ads/{adId}")
    public AdResource getAdById(@PathVariable Long adId){
        return convertToResource(adService.getAdById(adId));
    }

    @Operation(summary = "Create Ad", description = "Create a new Ad", tags = {"ads"})
    @PostMapping("/ads")
    public AdResource createAd(@Valid @RequestBody SaveAdResource resource){
        Ad ad = convertToEntity(resource);
        return convertToResource(adService.createAd(resource.getPropertyId(),ad));
    }

    @Operation(summary = "Update Ad", description = "Update Ad", tags = {"ads"})
    @PutMapping("/ads/{adId}")
    public AdResource updateAd(
            @PathVariable Long adId,
            @RequestBody @Valid SaveAdResource resource){
        Ad ad = convertToEntity(resource);
        return convertToResource(adService.updateAd(adId, ad));
    }

    @Operation(summary = "Delete Ad", description = "Delete Ad", tags = {"ads"})
    @DeleteMapping("/ads/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId){
        return adService.deleteAd(adId);
    }

    private  Ad convertToEntity(SaveAdResource resource){return mapper.map(resource,Ad.class);}
    private  AdResource convertToResource(Ad entity){return mapper.map(entity,AdResource.class);}
}

package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import com.acme.meetyourroommate.domain.service.PropertyDetailService;
import com.acme.meetyourroommate.resource.PropertyDetailResource;
import com.acme.meetyourroommate.resource.save.SavePropertyDetailResource;
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
public class PropertyDetailController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PropertyDetailService propertyDetailService;

    @Operation(summary = "Get All PropertyDetails", description = "Get all propertyDetails", tags = {"property details"})
    @GetMapping("/property-details")
    public Page<PropertyDetailResource> getAllPropertyDetails(Pageable pageable){
        Page<PropertyDetail> propertyDetailPage = propertyDetailService.getAllPropertyDetails(pageable);

        List<PropertyDetailResource> resources = propertyDetailPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get PropertyDetail By Id", description = "Get PropertyDetail By Id", tags = {"property details"})
    @GetMapping("/property-details/{propertyDetailId}")
    public PropertyDetailResource getPropertyDetailById(@PathVariable Long propertyDetailId){
        return convertToResource(propertyDetailService.getPropertyDetailById(propertyDetailId));
    }

    @Operation(summary = "Create PropertyDetail", description = "Create a new PropertyDetail", tags = {"property details"})
    @PostMapping("properties/{propertyId}/property-details")
    public PropertyDetailResource createPropertyDetail(@Valid @RequestBody SavePropertyDetailResource resource, @PathVariable Long propertyId){
        PropertyDetail propertyDetail = convertToEntity(resource);
        return convertToResource(propertyDetailService.createPropertyDetail(propertyId,propertyDetail));
    }

    @Operation(summary = "Update PropertyDetail", description = "Update PropertyDetail", tags = {"property details"})
    @PutMapping("/property-details/{propertyDetailId}")
    public PropertyDetailResource updatePropertyDetail(
            @PathVariable Long propertyDetailId,
            @RequestBody @Valid SavePropertyDetailResource resource){
        PropertyDetail propertyDetail = convertToEntity(resource);
        return convertToResource(propertyDetailService.updatePropertyDetail(propertyDetailId, propertyDetail));
    }

    @Operation(summary = "Delete PropertyDetail", description = "Delete PropertyDetail", tags = {"property details"})
    @DeleteMapping("/property-details/{propertyDetailId}")
    public ResponseEntity<?> deletePropertyDetail(@PathVariable Long propertyDetailId){
        return propertyDetailService.deletePropertyDetail(propertyDetailId);
    }

    private  PropertyDetail convertToEntity(SavePropertyDetailResource resource){return mapper.map(resource,PropertyDetail.class);}
    private  PropertyDetailResource convertToResource(PropertyDetail entity){return mapper.map(entity,PropertyDetailResource.class);}

}

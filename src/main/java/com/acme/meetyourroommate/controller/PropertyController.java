package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.service.PropertyService;
import com.acme.meetyourroommate.resource.PropertyResource;
import com.acme.meetyourroommate.resource.save.SavePropertyResource;
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
public class PropertyController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Get All Properties", description = "Get all properties", tags = {"properties"})
    @GetMapping("/properties")
    public Page<PropertyResource> getAllProperties(Pageable pageable){
        Page<Property> propertyPage = propertyService.getAllProperties(pageable);

        List<PropertyResource> resources = propertyPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Property By Id", description = "Get Property By Id", tags = {"properties"})
    @GetMapping("/properties/{propertyId}")
    public PropertyResource getPropertyById(@PathVariable Long propertyId){
        return convertToResource(propertyService.getPropertyById(propertyId));
    }

    @Operation(summary = "Create Property", description = "Create a new Property", tags = {"properties"})
    @PostMapping("/properties")
    public PropertyResource createProperty(@Valid @RequestBody SavePropertyResource resource,
                                           @RequestParam("lessor") Long lessorId){
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.createProperty(lessorId,property));
    }

    @Operation(summary = "Update Property", description = "Update Property", tags = {"properties"})
    @PutMapping("/properties/{propertyId}")
    public PropertyResource updateProperty(
            @PathVariable Long propertyId,
            @RequestBody @Valid SavePropertyResource resource){
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.updateProperty(propertyId, property));
    }

    @Operation(summary = "Delete Property", description = "Delete Property", tags = {"properties"})
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId){
        return propertyService.deleteProperty(propertyId);
    }

    private  Property convertToEntity(SavePropertyResource resource){return mapper.map(resource,Property.class);}
    private  PropertyResource convertToResource(Property entity){return mapper.map(entity,PropertyResource.class);}

}

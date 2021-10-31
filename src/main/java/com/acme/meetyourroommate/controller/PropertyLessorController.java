package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.service.PropertyService;
import com.acme.meetyourroommate.resource.PropertyResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PropertyLessorController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Get All Properties by Lessor", description = "Get all properties by Lessor", tags = {"properties"})
    @GetMapping("/lessor/{lessorId}/properties")
    public Page<PropertyResource> getAllPropertiesByLessorId(@PathVariable Long lessorId, Pageable pageable){
        Page<Property> propertyPage = propertyService.getAllPropertiesByLessorId(lessorId,pageable);

        List<PropertyResource> resources = propertyPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Property By Id And LessorId", description = "Get Property By Id And LessorId", tags = {"properties"})
    @GetMapping("/lessor/{lessorId}/properties/{propertyId}")
    public PropertyResource getPropertyByIdAndLessorId(@PathVariable Long lessorId,@PathVariable Long propertyId){
        return convertToResource(propertyService.getPropertyByIdAndLessorId(lessorId,propertyId));
    }
    private  PropertyResource convertToResource(Property entity){return mapper.map(entity,PropertyResource.class);}
}

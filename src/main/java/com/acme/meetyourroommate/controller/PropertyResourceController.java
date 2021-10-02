package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.PropertyResource;
import com.acme.meetyourroommate.domain.service.PropertyResourceService;
import com.acme.meetyourroommate.resource.PropertyResourceResource;
import com.acme.meetyourroommate.resource.save.SavePropertyResourceResource;
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
public class PropertyResourceController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PropertyResourceService propertyResourceService;

    @Operation(summary = "Get All PropertyResources", description = "Get all propertyResources", tags = {"property resources"})
    @GetMapping("/property-resources")
    public Page<PropertyResourceResource> getAllPropertyResources(Pageable pageable){
        Page<PropertyResource> propertyResourcePage = propertyResourceService.getAllPropertyResources(pageable);

        List<PropertyResourceResource> resources = propertyResourcePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get PropertyResource By Id", description = "Get PropertyResource By Id", tags = {"property resources"})
    @GetMapping("/property-resources/{propertyResourceId}")
    public PropertyResourceResource getPropertyResourceById(@PathVariable Long propertyResourceId){
        return convertToResource(propertyResourceService.getPropertyResourceById(propertyResourceId));
    }

    @Operation(summary = "Create PropertyResource", description = "Create a new PropertyResource", tags = {"property resources"})
    @PostMapping("property-details/{propertyDetailId}/property-resources")
    public PropertyResourceResource createPropertyResource(@Valid @RequestBody SavePropertyResourceResource resource, @PathVariable Long propertyDetailId){
        PropertyResource propertyResource = convertToEntity(resource);
        return convertToResource(propertyResourceService.createPropertyResource(propertyDetailId,propertyResource));
    }

    @Operation(summary = "Update PropertyResource", description = "Update PropertyResource", tags = {"property resources"})
    @PutMapping("/property-resources/{propertyResourceId}")
    public PropertyResourceResource updatePropertyResource(
            @PathVariable Long propertyResourceId,
            @RequestBody @Valid SavePropertyResourceResource resource){
        PropertyResource propertyResource = convertToEntity(resource);
        return convertToResource(propertyResourceService.updatePropertyResource(propertyResourceId, propertyResource));
    }

    @Operation(summary = "Delete PropertyResource", description = "Delete PropertyResource", tags = {"property resources"})
    @DeleteMapping("/property-resources/{propertyResourceId}")
    public ResponseEntity<?> deletePropertyResource(@PathVariable Long propertyResourceId){
        return propertyResourceService.deletePropertyResource(propertyResourceId);
    }

    private  PropertyResource convertToEntity(SavePropertyResourceResource resource){return mapper.map(resource,PropertyResource.class);}
    private  PropertyResourceResource convertToResource(PropertyResource entity){return mapper.map(entity,PropertyResourceResource.class);}

}

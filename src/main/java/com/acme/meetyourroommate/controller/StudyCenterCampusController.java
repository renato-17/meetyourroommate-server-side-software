package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.service.CampusService;
import com.acme.meetyourroommate.resource.CampusResource;
import com.acme.meetyourroommate.resource.save.SaveCampusResource;
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
public class StudyCenterCampusController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CampusService campusService;

    @Operation(summary = "Get All Campuses By Study center Id", description = "Get all campuses by study center Id", tags = {"campuses"})
    @GetMapping("/study-centers/{id}/campuses")
    public Page<CampusResource> getAllCampusesByStudyCenter(@PathVariable Long id, Pageable pageable){
        Page<Campus> campusPage = campusService.getAllCampusesByStudyCenterId(id,pageable);

        List<CampusResource> resources = campusPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Campus By Id And Study Center Id", description = "Get Campus By Id And Study Center Id", tags = {"campuses"})
    @GetMapping("/study-centers/{studyCenterId}/campuses/{campusId}")
    public CampusResource getCampusByIdAndStudyCenterId(@PathVariable Long studyCenterId,@PathVariable Long campusId){
        return convertToResource(campusService.getCampusByIdAndStudyCenterId(studyCenterId,campusId));
    }

    private CampusResource convertToResource(Campus entity){return mapper.map(entity,CampusResource.class);}
}

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CampusController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CampusService campusService;

    @Operation(summary = "Get All Campuses", description = "Get all campuses", tags = {"campuses"})
    @GetMapping("/campuses")
    public Page<CampusResource> getAllCampuses(Pageable pageable){
        Page<Campus> campusPage = campusService.getAllCampus(pageable);

        List<CampusResource> resources = campusPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Campus By Id", description = "Get Campus By Id", tags = {"campuses"})
    @GetMapping("/campuses/{campusId}")
    public CampusResource getCampusById(@PathVariable Long campusId){
        return convertToResource(campusService.getCampusById(campusId));
    }

    @Operation(summary = "Create Campus", description = "Create a new Campus", tags = {"campuses"})
    @PostMapping("/campuses")
    public CampusResource createCampus(@Valid @RequestBody SaveCampusResource resource,
                                       @RequestParam("study-center") Long studyCenterId){
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.createCampus(studyCenterId,campus));
    }

    @Operation(summary = "Update Campus", description = "Update Campus", tags = {"campuses"})
    @PutMapping("/campuses/{campusId}")
    public CampusResource updateCampus(
            @PathVariable Long campusId,
            @RequestBody @Valid SaveCampusResource resource){
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.updateCampus(campusId, campus));
    }

    @Operation(summary = "Delete Campus", description = "Delete Campus", tags = {"campuses"})
    @DeleteMapping("/campuses/{campusId}")
    public ResponseEntity<?> deleteCampus(@PathVariable Long campusId){
        return campusService.deleteCampus(campusId);
    }

    private  Campus convertToEntity(SaveCampusResource resource){return mapper.map(resource,Campus.class);}
    private  CampusResource convertToResource(Campus entity){return mapper.map(entity,CampusResource.class);}
}

package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.StudyCenter;
import com.acme.meetyourroommate.domain.service.StudyCenterService;
import com.acme.meetyourroommate.resource.StudyCenterResource;
import com.acme.meetyourroommate.resource.save.SaveStudyCenterResource;
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
public class StudyCenterController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudyCenterService studyCenterService;

    @Operation(summary = "Get All StudyCenters", description = "Get all study centers", tags = {"study centers"})
    @GetMapping("/studyCenters")
    public Page<StudyCenterResource> getAllStudyCenters(Pageable pageable){
        Page<StudyCenter> studyCenterPage = studyCenterService.getAllStudyCenters(pageable);

        List<StudyCenterResource> resources = studyCenterPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get StudyCenter By Id", description = "Get StudyCenter By Id", tags = {"study centers"})
    @GetMapping("/studyCenters/{studyCenterId}")
    public StudyCenterResource getStudyCenterById(@PathVariable Long studyCenterId){
        return convertToResource(studyCenterService.getStudyCenterById(studyCenterId));
    }

    @Operation(summary = "Create StudyCenter", description = "Create a new studyCenter", tags = {"study centers"})
    @PostMapping("/studyCenters")
    public StudyCenterResource createStudyCenter(@Valid @RequestBody SaveStudyCenterResource resource){
        StudyCenter studyCenter = convertToEntity(resource);
        return convertToResource(studyCenterService.createStudyCenter(studyCenter));
    }

    @Operation(summary = "Update StudyCenter", description = "Update a studyCenter", tags = {"study centers"})
    @PutMapping("/studyCenters/{studyCenterId}")
    public StudyCenterResource updateStudyCenter(
            @PathVariable Long studyCenterId,
            @RequestBody @Valid SaveStudyCenterResource resource){
        StudyCenter studyCenter = convertToEntity(resource);
        return convertToResource(studyCenterService.updateStudyCenter(studyCenterId, studyCenter));
    }

    @Operation(summary = "Delete a studyCenter", description = "Delete a studyCenter", tags = {"study centers"})
    @DeleteMapping("/studyCenters/{studyCenterId}")
    public ResponseEntity<?> deleteStudyCenter(@PathVariable Long studyCenterId){
        return studyCenterService.deleteStudyCenter(studyCenterId);
    }

    private  StudyCenter convertToEntity(SaveStudyCenterResource resource){return mapper.map(resource,StudyCenter.class);}
    private  StudyCenterResource convertToResource(StudyCenter entity){return mapper.map(entity,StudyCenterResource.class);}
}

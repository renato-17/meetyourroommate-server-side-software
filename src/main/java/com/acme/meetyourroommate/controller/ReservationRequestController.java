package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.ReservationRequest;
import com.acme.meetyourroommate.domain.service.ReservationRequestService;
import com.acme.meetyourroommate.resource.ReservationRequestResource;
import com.acme.meetyourroommate.resource.save.SaveReservationRequestResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReservationRequestController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ReservationRequestService reservationRequestService;

    @Operation(summary = "Get All Reservation", description = "Get All Reservation", tags = {"reservation"})
    @GetMapping("/reservations")
    public Page<ReservationRequestResource> getAllReservations(Pageable pageable){
        Page<ReservationRequest> reservationRequestPage = reservationRequestService.findAllReservationRequests(pageable);

        List<ReservationRequestResource> resources = reservationRequestPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Reservation", description = "Create an reservation", tags = {"reservation"})
    @PostMapping("/reservations")
    public ReservationRequestResource createReservation(@Valid @RequestBody SaveReservationRequestResource resource){
        ReservationRequest reservationRequest = convertToEntity(resource);
        return convertToResource(reservationRequestService.createReservationRequest(resource.getTeamId(), resource.getLessorId(), reservationRequest));
    }

    @Operation(summary = "Get Reservation by TeamId And LessorId", description = "Get an Reservation by TeamId and LessorId", tags = {"reservation"})
    @GetMapping("/reservations/{teamId}/{lessorId}")
    public ReservationRequestResource getReservationByTeamIdAndLessorId(@PathVariable Long teamId, @PathVariable Long lessorId){
        return convertToResource(reservationRequestService.findByTeamIdAndLessorId(teamId, lessorId));
    }

    private ReservationRequest convertToEntity(SaveReservationRequestResource resource) {
        return mapper.map(resource, ReservationRequest.class);
    }
    private ReservationRequestResource convertToResource(ReservationRequest entity) {
        return mapper.map(entity, ReservationRequestResource.class);
    }
}

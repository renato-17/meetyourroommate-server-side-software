package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Comment;
import com.acme.meetyourroommate.domain.service.CommentService;
import com.acme.meetyourroommate.resource.CommentResource;
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
public class AdCommentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommentService commentService;

    @Operation(summary = "Get All Comments by Ad", description = "Get all comments by Ad", tags = {"comments"})
    @GetMapping("/ad/{adId}/comments")
    public Page<CommentResource> getAllCommentsByAdId(@PathVariable Long adId, Pageable pageable){
        Page<Comment> commentPage = commentService.getAllCommentsByAdId(adId,pageable);

        List<CommentResource> resources = commentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Comment By Id And AdId", description = "Get Comment By Id And AdId", tags = {"comments"})
    @GetMapping("/ad/{adId}/comments/{commentId}")
    public CommentResource getCommentByIdAndAdId(@PathVariable Long adId,@PathVariable Long commentId){
        return convertToResource(commentService.getCommentByIdAndAdId(adId,commentId));
    }
    private  CommentResource convertToResource(Comment entity){return mapper.map(entity,CommentResource.class);}
}

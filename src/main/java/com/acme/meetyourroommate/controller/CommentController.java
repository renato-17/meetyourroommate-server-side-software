package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Comment;
import com.acme.meetyourroommate.domain.service.CommentService;
import com.acme.meetyourroommate.resource.CommentResource;
import com.acme.meetyourroommate.resource.save.SaveCommentResource;
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
public class CommentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommentService commentService;

    @Operation(summary = "Get All Comments", description = "Get all comments", tags = {"comments"})
    @GetMapping("/comments")
    public Page<CommentResource> getAllComments(Pageable pageable){
        Page<Comment> commentPage = commentService.getAllComments(pageable);

        List<CommentResource> resources = commentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Comment By Id", description = "Get Comment By Id", tags = {"comments"})
    @GetMapping("/comments/{commentId}")
    public CommentResource getCommentById(@PathVariable Long commentId){
        return convertToResource(commentService.getCommentById(commentId));
    }

    @Operation(summary = "Create Comment", description = "Create a new Comment", tags = {"comments"})
    @PostMapping("/comments")
    public CommentResource createComment(@Valid @RequestBody SaveCommentResource resource,
                                         @RequestParam("ad") Long adId){
        Comment comment = convertToEntity(resource);
        return convertToResource(commentService.createComment(adId,comment));
    }

    @Operation(summary = "Update Comment", description = "Update Comment", tags = {"comments"})
    @PutMapping("/comments/{commentId}")
    public CommentResource updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid SaveCommentResource resource){
        Comment comment = convertToEntity(resource);
        return convertToResource(commentService.updateComment(commentId, comment));
    }

    @Operation(summary = "Delete Comment", description = "Delete Comment", tags = {"comments"})
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }

    private  Comment convertToEntity(SaveCommentResource resource){return mapper.map(resource,Comment.class);}
    private  CommentResource convertToResource(Comment entity){return mapper.map(entity,CommentResource.class);}

}

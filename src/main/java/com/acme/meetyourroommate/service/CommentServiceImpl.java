package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Comment;
import com.acme.meetyourroommate.domain.repository.AdRepository;
import com.acme.meetyourroommate.domain.repository.CommentRepository;
import com.acme.meetyourroommate.domain.service.CommentService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AdRepository adRepository;

    @Override
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
    }

    @Override
    public Page<Comment> getAllCommentsByAdId(Long adId, Pageable pageable) {
        return commentRepository.findByAdId(adId,pageable);
    }

    @Override
    public Comment getCommentByIdAndAdId(Long adId, Long commentId) {
        return commentRepository.findByIdAndAdId(adId,commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
    }

    @Override
    public Comment createComment(Long adId, Comment comment) {
        return adRepository.findById(adId).map(ad ->{
            comment.setAd(ad);
            return commentRepository.save(comment);
        }).orElseThrow(()->new ResourceNotFoundException("Ad","Id",adId));
    }

    @Override
    public Comment updateComment(Long commentId, Comment commentRequest) {
        Comment comment =  commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        comment.setText(commentRequest.getText());
        return commentRepository.save(comment);
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId) {
        Comment comment =  commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }
}

package CUHA.homepage.controller;

import CUHA.homepage.model.Comment;
import CUHA.homepage.security.dto.commentDTO.CommentMessageResponse;
import CUHA.homepage.security.dto.commentDTO.CommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentResponse;
import CUHA.homepage.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentMessageResponse addComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return commentService.addComment(commentRequest,request);
    }

    @GetMapping("/comments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/comment")
    public List<CommentResponse> getCommentByBoard(@RequestParam Long id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("/comment")
    public CommentMessageResponse updateComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return null;
    }

    @DeleteMapping("/comment")
    public CommentMessageResponse deleteComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return null;
    }


}

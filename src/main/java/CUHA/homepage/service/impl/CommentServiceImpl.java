package CUHA.homepage.service.impl;

import CUHA.homepage.model.Comment;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.repository.CommentRepository;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.commentDTO.AllCommentsResponse;
import CUHA.homepage.security.dto.commentDTO.CommentMessageResponse;
import CUHA.homepage.security.dto.commentDTO.CommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentResponse;
import CUHA.homepage.service.CommentService;
import CUHA.homepage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    @Override
    public CommentMessageResponse addComment(CommentRequest commentRequest, HttpServletRequest request) {
        Comment comment = Comment.builder()
                .comment(commentRequest.getComment())
                .board(boardRepository.findById(commentRequest.getBoard_id()).get())
                .author(userRepository.findByUsername(request.getSession().getAttribute("user").toString()).get())
                .created_at(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        return CommentMessageResponse.builder().message("댓글이 등록되었습니다.").build();
    }

    @Override
    public List<CommentResponse> getAllComments() {

        return commentRepository.findAll().stream().map(x->CommentResponse.builder()
                .id(x.getId())
                .comment(x.getComment())
                .author(x.getAuthor().getUsername()).build()).toList();
    }

    @Override
    public List<CommentResponse> getCommentById(Long id) {
        return commentRepository.findAllByBoard_Id(id).stream().map(x->CommentResponse.builder()
                .id(x.getId()).comment(x.getComment()).author(x.getAuthor().getUsername()).build()).toList();
    }

    @Override
    public CommentMessageResponse deleteComment(CommentRequest commentRequest) {

        return null;
    }

    @Override
    public CommentMessageResponse updateComment(CommentRequest commentRequest) {
        return null;
    }
}

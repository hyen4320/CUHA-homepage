package CUHA.homepage.service.impl;

import CUHA.homepage.model.Board;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.boardDTO.BoardRequest;
import CUHA.homepage.security.dto.boardDTO.BoardResponse;
import CUHA.homepage.security.dto.boardDTO.BoardmessageResponse;
import CUHA.homepage.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public BoardmessageResponse addBoard(HttpServletRequest request, BoardRequest board) {
        HttpSession session = request.getSession();


        Board saveboard = Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .author(userRepository.findByUsername(request.getSession().getAttribute("user").toString()).get())
                .build();
        boardRepository.save(saveboard);
        return BoardmessageResponse.builder()
                .message("작성이 완료 되었습니다.").build();
    }

    @Override
    public BoardResponse getBoard(Long id) {
        Board findBoard=boardRepository.findById(id).get();
        return BoardResponse.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .author(findBoard.getAuthor().getUsername())
                .build();
    }

    @Override
    public void deleteBoard(Long id) {

    }

    @Override
    public BoardmessageResponse updateBoard(Board board) {
        return null;
    }

    @Override
    public List<BoardResponse> getBoards() {
        return boardRepository.findAll().stream().map(x-> BoardResponse.builder()
                .id(x.getId())
                .author(x.getAuthor().getUsername())
                .title(x.getTitle())
                .content(x.getContent())
                .build()).toList();
    }
}

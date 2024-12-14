package CUHA.homepage.service;

import CUHA.homepage.model.Board;
import CUHA.homepage.security.dto.boardDTO.BoardRequest;
import CUHA.homepage.security.dto.boardDTO.BoardResponse;
import CUHA.homepage.security.dto.boardDTO.BoardmessageResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface BoardService {
    BoardmessageResponse addBoard(HttpServletRequest request, BoardRequest board);
    BoardResponse getBoard(Long id);
    void deleteBoard(Long id);
    BoardmessageResponse updateBoard(Board board);
    List<BoardResponse> getBoards();
}

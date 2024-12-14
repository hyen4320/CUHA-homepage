package CUHA.homepage.service;

import CUHA.homepage.model.Board;

import java.util.List;

public interface ExamService {
    void addBoard(Board board);
    Board getBoard(Long id);
    void deleteBoard(Long id);
    void updateBoard(Board board);
    List<Board> getBoards();
}

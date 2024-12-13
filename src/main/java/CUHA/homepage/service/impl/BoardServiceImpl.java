package CUHA.homepage.service.impl;

import CUHA.homepage.model.Board;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;

    @Override
    public void addBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long id) {
        boardRepository.findById(id);
        return null;
    }

    @Override
    public void deleteBoard(int id) {

    }

    @Override
    public void updateBoard(Board board) {

    }

    @Override
    public List<Board> getBoards() {
        return List.of();
    }
}

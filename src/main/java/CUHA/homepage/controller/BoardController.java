package CUHA.homepage.controller;

import CUHA.homepage.security.dto.boardDTO.*;
import CUHA.homepage.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borad")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @PostMapping("/post")
    public BoardmessageResponse postBoard(@RequestBody BoardRequest boardRequest, HttpServletRequest request) {
        return boardService.addBoard(request,boardRequest);
    }

    @GetMapping("/getBoard")
    public BoardResponse getBoard(@RequestParam Long id) {
        return boardService.getBoard(id);
    }

    @GetMapping("/getBoards")
    public List<BoardResponse> getBoard() {
        return boardService.getBoards();
    }

    @PutMapping("/updateBoard")
    public BoardmessageResponse updateBoard(@RequestBody BoardFindRequest boardFindRequest, HttpServletRequest request) {
        return boardService.updateBoard(boardFindRequest,request);
    }

    @DeleteMapping("/deleteboard")
    public BoardmessageResponse deleteBoard(@RequestParam Long id, HttpServletRequest request) {
        return boardService.deleteBoard(id,request);
    }


}

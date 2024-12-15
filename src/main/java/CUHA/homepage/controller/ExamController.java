package CUHA.homepage.controller;

import CUHA.homepage.security.dto.boardDTO.BoardFindRequest;
import CUHA.homepage.security.dto.boardDTO.BoardRequest;
import CUHA.homepage.security.dto.boardDTO.BoardResponse;
import CUHA.homepage.security.dto.boardDTO.BoardmessageResponse;
import CUHA.homepage.security.dto.examDTO.*;
import CUHA.homepage.service.BoardService;
import CUHA.homepage.service.ExamService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    @PostMapping("/post")
    public ExamMessageResponse postExam(@RequestBody ExamRequest examRequest, HttpServletRequest request) {
        return examService.addExam(examRequest,request);
    }

    @GetMapping("/getBoard")
    public ExamFindResponse getExam(@RequestParam Long id) {
        return examService.getExam(id);
    }

    @GetMapping("/getBoards")
    public List<ExamFindResponse> getExam() {
        return examService.getExams();
    }

    @PutMapping("/updateBoard")
    public ExamMessageResponse updateExam(@RequestBody ExamUpdateRequeest examUpdateRequeest, HttpServletRequest request) {
        return examService.updateExam(examUpdateRequeest,request);
    }

    @DeleteMapping("/deleteboard")
    public ExamMessageResponse deleteExam(@RequestParam Long id, HttpServletRequest request) {
        return examService.deleteExam(id,request);
    }

    @PostMapping("/checkAnswer")
    public ExamMessageResponse checkAnswer(@RequestBody ExamAnswerRequest answer, HttpServletRequest request) {
        return examService.checkAnswer(answer,request);
    }

}

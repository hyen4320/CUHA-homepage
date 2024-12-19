package CUHA.homepage.controller;

import CUHA.homepage.security.dto.fileDTO.ExamFileResponse;
import CUHA.homepage.security.dto.fileDTO.FileRequest;
import CUHA.homepage.security.dto.fileDTO.FileResponse;
import CUHA.homepage.security.dto.fileDTO.GeneralFileResponse;
import CUHA.homepage.service.BoardFileService;
import CUHA.homepage.service.ExamFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@CrossOrigin
@RestController

@RequiredArgsConstructor
public class FileController {
    private final BoardFileService boardFileService;
    private final ExamFileService examFileService;
    @PostMapping(value = "/generalfilesave",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileResponse>addGeneralFile(@RequestPart MultipartFile file, FileRequest fileRequest) throws IOException {

        return ResponseEntity.ok(boardFileService.saveFile(file,fileRequest));

    }
    @PostMapping(value = "/examfilesave",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileResponse>addExamFile(@RequestPart MultipartFile file, FileRequest fileRequest) throws IOException {

        return ResponseEntity.ok(examFileService.saveFile(file,fileRequest));
    }
    @GetMapping("/examfile")
    public ResponseEntity<List<ExamFileResponse>>fildExamFile(@RequestParam FileRequest fileId) throws IOException {
        return ResponseEntity.ok(examFileService.getFile(fileId));
    }
    @GetMapping("generalfile")
    public ResponseEntity<List<GeneralFileResponse>>findExamFile(@RequestParam FileRequest fileId) throws IOException {
        return ResponseEntity.ok(boardFileService.getFile(fileId));
    }

    @GetMapping(value = "/boardfiledownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource>downloadBoardFile(@RequestParam Long id) throws IOException {
        String fileName= boardFileService.findFilename(id).getMessage();
        String encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
                .body(boardFileService.downloadFile(id));
    }
    @GetMapping(value = "/boardfiledownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource>downloadExamFile(@RequestParam Long id) throws IOException {
        String fileName= examFileService.findFilename(id).getMessage();
        String encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
                .body(examFileService.downloadFile(id));
    }
}

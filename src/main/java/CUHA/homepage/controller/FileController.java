package CUHA.homepage.controller;

import CUHA.homepage.security.dto.fileDTO.ExamFileResponse;
import CUHA.homepage.security.dto.fileDTO.FileRequest;
import CUHA.homepage.security.dto.fileDTO.FileResponse;
import CUHA.homepage.security.dto.fileDTO.GeneralFileResponse;
import CUHA.homepage.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@CrossOrigin
@RestController

@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping(value = "/generalfilesave",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileResponse>addGeneralFile(@RequestPart MultipartFile file, FileRequest fileRequest) throws IOException {

        return ResponseEntity.ok(fileService.saveFile(file,fileRequest));

    }
    @PostMapping(value = "/examfilesave",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileResponse>addExamFile(@RequestPart MultipartFile file, FileRequest fileRequest) throws IOException {

        return ResponseEntity.ok(fileService.saveExamFIle(file,fileRequest));
    }
    @GetMapping("/examfile")
    public ResponseEntity<List<ExamFileResponse>>fildExamFile(@RequestParam FileRequest fileId) throws IOException {
        return ResponseEntity.ok(fileService.getExamFIle(fileId));
    }
    @GetMapping("generalfile")
    public ResponseEntity<List<GeneralFileResponse>>findExamFile(@RequestParam FileRequest fileId) throws IOException {
        return ResponseEntity.ok(fileService.getFile(fileId));
    }

    @GetMapping(value = "/filedownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource>downloadFile(@RequestParam Long id) throws IOException {
        String fileName= fileService.findFilename(id).getMessage();
        String encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
        System.out.println(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
                .body(fileService.downloadFile(id));
    }

}

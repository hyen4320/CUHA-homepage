package CUHA.homepage.controller;

import CUHA.homepage.security.dto.fileDTO.FileRequest;
import CUHA.homepage.security.dto.fileDTO.FileResponse;
import CUHA.homepage.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping(value = "/filesave",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileResponse>addFile(@RequestPart MultipartFile file, FileRequest fileRequest) throws IOException {

        return ResponseEntity.ok(fileService.saveFile(file,fileRequest));

    }
}

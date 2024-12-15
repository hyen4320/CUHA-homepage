package CUHA.homepage.service;

import CUHA.homepage.security.dto.fileDTO.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {
    FileResponse saveFile(MultipartFile file,FileRequest request) throws IOException;
    List<GeneralFileResponse> getFile(FileRequest request);
    InputStreamResource downloadFile(Long id) throws FileNotFoundException;
    FileResponse saveExamFIle(MultipartFile file,FileRequest request) throws IOException;
    List<ExamFileResponse> getExamFIle(FileRequest request);
    FileResponse deleteFile(FileRequest request);
    FileResponse findFilename(Long id);
}

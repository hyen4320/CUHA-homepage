package CUHA.homepage.service.impl;

import CUHA.homepage.model.Category;
import CUHA.homepage.model.File;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.repository.ExamRepository;
import CUHA.homepage.repository.FileRepository;
import CUHA.homepage.security.dto.fileDTO.*;
import CUHA.homepage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final ExamRepository examRepository;
    private final BoardRepository boardRepository;
    @Override
    public FileResponse saveFile(MultipartFile file, FileRequest filerequest) throws IOException {
        Path downPath = Paths.get("src", "main", "resources", "static", "Files");
        if(file.isEmpty()){
            throw new NotFoundException("파일이 존재하지 않습니다.");
        }

        String originalFileName = file.getOriginalFilename();
        String extension = Optional.ofNullable(originalFileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(originalFileName.lastIndexOf(".") + 1))
                .orElseThrow(() -> new IllegalArgumentException("파일 확장자를 찾을 수 없습니다."));

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "." + extension;

        Path filePath = downPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        fileRepository.save(File.builder()
                .fileExtension(extension)
                .board(boardRepository.findById(filerequest.getBoard_id()).get())
                .uuid(uuid)
                .originalFileName(Normalizer.normalize(file.getOriginalFilename(),Normalizer.Form.NFC))
                //원래는 NFD방식으로 이름을 가져와 한->ㅎㅏㄴ이렇게 되어서 정규화 해줌
                .fileLoc(filePath.toString())
                .created_at(LocalDateTime.now())
                .build());

        return FileResponse.builder().message("저장에 성공했습니다.").build();
    }

    @Override
    public List<GeneralFileResponse> getFile(FileRequest fileRequest) {
        Optional<List<File>> files=fileRepository.findAllByBoard(fileRequest.getBoard_id());
        if(!files.isPresent()){
            throw new NotFoundException("해당 게시글이 없습니다.");
        }
        return files.get().stream().map(x->GeneralFileResponse.builder().
                filename(x.getOriginalFileName()).build()).toList();


    }

    @Override
    public java.io.File downloadFile(ExamFileRequest fileRequest) {
        Optional<File> file=fileRepository.findByoriginalFileName(fileRequest.getFileName());
        Path downPath = Paths.get("src", "main", "resources", "static", "Files");
        if(!file.isPresent()){
            throw new NotFoundException("그런 파일 없습니다.");
        }
        File downloadFile = new File();
        return null;
    }

    @Override
    public FileResponse saveExamFIle(MultipartFile file, FileRequest filerequest) throws IOException {
        Category category=fileRepository.findByExam(filerequest.getExam_id()).get().getExam().getCategory();
        Path downPath = Paths.get("src", "main", "resources", "static", "CTF", category.name());
        if(file.isEmpty()){
            throw new NotFoundException("파일이 존재하지 않습니다.");
        }

        String originalFileName = file.getOriginalFilename();
        String extension = Optional.ofNullable(originalFileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(originalFileName.lastIndexOf(".") + 1))
                .orElseThrow(() -> new IllegalArgumentException("파일 확장자를 찾을 수 없습니다."));

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "." + extension;

        Path filePath = downPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        fileRepository.save(File.builder()
                .fileExtension(extension)
                .exam(examRepository.findById(filerequest.getExam_id()).get())
                .uuid(uuid)
                .originalFileName(Normalizer.normalize(file.getOriginalFilename(),Normalizer.Form.NFC))
                //원래는 NFD방식으로 이름을 가져와 한->ㅎㅏㄴ이렇게 되어서 정규화 해줌
                .fileLoc(filePath.toString())
                .created_at(LocalDateTime.now())
                .build());

        return FileResponse.builder().message("저장에 성공했습니다.").build();
    }

    @Override
    public List<ExamFileResponse> getExamFIle(FileRequest fileRequest) {
        Optional<List<File>> files=fileRepository.findAllByExam(fileRequest.getExam_id());
        if(!files.isPresent()){
            throw new NotFoundException("해당 게시글이 없습니다.");
        }
        return files.get().stream().map(x->ExamFileResponse.builder().
                fileName(x.getOriginalFileName()).build()).toList();

    }

    @Override
    public FileResponse deleteFile(FileRequest request) {
        return null;
    }
}

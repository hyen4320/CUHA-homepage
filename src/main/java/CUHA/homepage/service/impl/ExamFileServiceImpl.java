package CUHA.homepage.service.impl;

import CUHA.homepage.model.Category;
import CUHA.homepage.model.ExamFile;
import CUHA.homepage.repository.BoardFileRepository;
import CUHA.homepage.repository.ExamFileRepository;
import CUHA.homepage.repository.ExamRepository;
import CUHA.homepage.security.dto.fileDTO.ExamFileResponse;
import CUHA.homepage.security.dto.fileDTO.ExamFileSaveRequest;
import CUHA.homepage.security.dto.fileDTO.FileResponse;
import CUHA.homepage.service.ExamFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class ExamFileServiceImpl implements ExamFileService {
    private final BoardFileRepository boardFileRepository;
    private final ExamRepository examRepository;
    private final ExamFileRepository examFileRepository;

    @Override
    public FileResponse saveFile(MultipartFile file, ExamFileSaveRequest examFileSaveRequest) throws IOException {
        Category category=examRepository.findById(examFileSaveRequest.getExam_id()).get().getCategory();
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
        examFileRepository.save(ExamFile.builder()
                .fileExtension(extension)
                .exam(examRepository.findById(examFileSaveRequest.getExam_id()).get())
                .uuid(uuid)
                .originalFileName(Normalizer.normalize(file.getOriginalFilename(),Normalizer.Form.NFC))
                //원래는 NFD방식으로 이름을 가져와 한->ㅎㅏㄴ이렇게 되어서 정규화 해줌
                .fileLoc(filePath.toString())
                .created_at(LocalDateTime.now())
                .build());

        return FileResponse.builder().message("저장에 성공했습니다.").build();
    }

    @Override
    public List<ExamFileResponse> getFile(ExamFileSaveRequest examFileSaveRequest) {
        Optional<List<ExamFile>> files= examFileRepository.findAllByExam_Id(examFileSaveRequest.getExam_id());
        if(!files.isPresent()){
            throw new NotFoundException("해당 게시글이 없습니다.");
        }
        return files.get().stream().map(x->ExamFileResponse.builder().
                fileName(x.getOriginalFileName()).id(x.getId()).build()).toList();
    }

    @Override
    public InputStreamResource downloadFile(Long id) throws FileNotFoundException {
        Optional<ExamFile> findfile= examFileRepository.findByExam_Id(id);
        if(!findfile.isPresent()){
            throw new NotFoundException("다운로드 가능한 파일이 없습니다.");
        }
        String fileLocation = findfile.get().getFileLoc();
        java.io.File fileToDownload = new java.io.File(fileLocation);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(fileToDownload));
        if (!fileToDownload.exists()) {
            throw new NotFoundException("파일 경로가 존재하지 않습니다: " + fileLocation);
        }
        return resource;
    }

    @Override
    public FileResponse deleteFile(List<Long> ids) {
        return null;
    }

    @Override
    public FileResponse findFilename(Long id) {
        return FileResponse.builder().message(examFileRepository.findByExam_Id(id).get().getOriginalFileName()).build();
    }
}
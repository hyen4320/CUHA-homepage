package CUHA.homepage.repository;


import CUHA.homepage.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<List<File>> findAllByBoard_Id(Long boardId);
    Optional<List<File>> findAllByExam_Id(Long examId);
    Optional<File> findByBoard_Id(Long boardId);
    Optional<File> findByExam_Id(Long examId);

    Optional<File> findByoriginalFileName(String originalFileName);
}

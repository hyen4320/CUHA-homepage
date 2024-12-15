package CUHA.homepage.repository;


import CUHA.homepage.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<List<File>> findAllByBoard(Long board);
    Optional<List<File>> findAllByExam(Long exam);
    Optional<File> findByBoard(Long board);
    Optional<File> findByExam(Long exam);
    Optional<File> findByoriginalFileName(String originalFileName);
}

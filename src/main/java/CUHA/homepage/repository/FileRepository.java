package CUHA.homepage.repository;


import CUHA.homepage.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}

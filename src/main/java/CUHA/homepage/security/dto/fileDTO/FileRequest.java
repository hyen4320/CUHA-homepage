package CUHA.homepage.security.dto.fileDTO;

import CUHA.homepage.model.Board;
import CUHA.homepage.model.Exam;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRequest {
    private String fileloc;
    private String fileExtension;
    private UUID uuid;
    private Board board_id;
    private Exam exam_id;
}
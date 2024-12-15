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
    private Long board_id;
    private Long exam_id;
}

package CUHA.homepage.security.dto.examDTO;

import CUHA.homepage.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequest {
    private String title;
    private String content;
    private User author;
    private String answer;
    private Long score;
}
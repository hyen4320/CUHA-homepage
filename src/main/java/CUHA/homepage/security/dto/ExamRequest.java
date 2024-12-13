package CUHA.homepage.security.dto;

import CUHA.homepage.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

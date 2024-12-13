package CUHA.homepage.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamAnswerRequest {
    private Long id;
    private String answer;
}

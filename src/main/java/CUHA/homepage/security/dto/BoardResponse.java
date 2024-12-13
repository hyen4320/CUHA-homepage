package CUHA.homepage.security.dto;

import CUHA.homepage.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {
    private User author;
    private String title;
    private String content;
}

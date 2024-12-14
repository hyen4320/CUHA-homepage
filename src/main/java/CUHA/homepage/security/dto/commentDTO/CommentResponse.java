package CUHA.homepage.security.dto.commentDTO;

import CUHA.homepage.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private User author;
    private String comment;
}

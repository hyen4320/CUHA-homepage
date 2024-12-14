package CUHA.homepage.security.dto.commentDTO;

import CUHA.homepage.model.Board;
import CUHA.homepage.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private Board board_id;
    private User author;
    private String comment;
}

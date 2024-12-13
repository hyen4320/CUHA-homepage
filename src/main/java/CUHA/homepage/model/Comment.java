package CUHA.homepage.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board_id;
    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;
    private String comment;
}

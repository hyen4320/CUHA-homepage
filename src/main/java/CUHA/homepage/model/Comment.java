package CUHA.homepage.model;

import jakarta.persistence.*;

@Entity
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

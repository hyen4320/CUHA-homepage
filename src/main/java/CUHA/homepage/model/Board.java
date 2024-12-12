package CUHA.homepage.model;

import jakarta.persistence.*;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;
    private String title;
    private String content;

}

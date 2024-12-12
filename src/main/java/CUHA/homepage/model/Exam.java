package CUHA.homepage.model;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private String answer;
    private Timestamp created_at;

}

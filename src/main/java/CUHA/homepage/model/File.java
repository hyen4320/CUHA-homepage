package CUHA.homepage.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileloc;
    private String fileExtension;
    private UUID uuid;
    @ManyToOne
    private Board board_id;
    @ManyToOne
    private Exam exam_id;
}

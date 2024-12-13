package CUHA.homepage.security.dto;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.UserRole;
import lombok.*;

import java.security.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserjoinRequest {
    private String username;
    private String password;
    private int score;
    private Gender gender;
}
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
    private Long score=0L;
    private boolean isActive;
    private Gender gender;
    private UserRole userRole;
}
}

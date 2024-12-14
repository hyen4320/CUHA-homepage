package CUHA.homepage.security.dto;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String password;
    private Long score;
    private boolean isActive;
    private Gender gender;
    private UserRole userRole;
}

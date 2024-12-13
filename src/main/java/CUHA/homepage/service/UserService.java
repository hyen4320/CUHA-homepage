package CUHA.homepage.service;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.User;
import CUHA.homepage.model.UserRole;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUser(Long id);
    List<User> getUsers();
    void deactivateUser(Long id);
    void activateUser(Long id);
    Long getScore(Long id);
    void setScore(Long id, Long score);
    void setUserRole(Long id, UserRole userRole);
    UserRole getUserRole(Long id);
    Gender getGender(Long id);
    Gender setGender(Long id, Gender gender);
}

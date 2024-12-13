package CUHA.homepage.service;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.User;
import CUHA.homepage.model.UserRole;
import CUHA.homepage.security.dto.*;

import java.util.List;

public interface UserService {
    UserJoinResponse addUser(UserjoinRequest user);
    UserRUDResponse updateUser(UserjoinRequest user);
    UserRUDResponse deleteUser(UserRUDRequest user);
    UserFindResponse getUser(UserRUDRequest user);
    List<UserFindResponse> getUsers();
    UserRUDResponse deactivateUser(UserRUDRequest user);
    UserRUDResponse activateUser(UserRUDRequest user);
    Long getScore(UserRUDRequest user);
    UserRUDResponse setScore(UserRUDRequest user, Long score);
    UserRUDResponse setUserRole(UserRUDRequest user, UserRole userRole);
    UserRole getUserRole(UserRUDRequest user);
    Gender getGender(UserRUDRequest user);
    Gender setGender(UserRUDRequest user, Gender gender);
}

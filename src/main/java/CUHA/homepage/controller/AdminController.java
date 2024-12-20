package CUHA.homepage.controller;

import CUHA.homepage.security.dto.userDTO.UserFindResponse;
import CUHA.homepage.security.dto.userDTO.UserRUDRequest;
import CUHA.homepage.security.dto.userDTO.UserRUDResponse;
import CUHA.homepage.security.dto.userDTO.UserUpdateRequest;
import CUHA.homepage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @PutMapping("/set/score")
    public UserRUDResponse setScore(@RequestBody UserRUDRequest username,Long score) {
        return userService.setScore(username, score);
    }

    @PutMapping("/set/updateUser")
    public UserRUDResponse setUserRole(@RequestBody UserUpdateRequest username, HttpServletRequest request) {
        return userService.updateUserAdmin(request,username);

    }

    @GetMapping("/findusers")
    public UserFindResponse findUsers(@RequestParam String username) {
        return userService.getUser(username);
    }
    @GetMapping("/getUsers")
    public List<UserFindResponse> getUsers() {
        return userService.getUsers();
    }
}

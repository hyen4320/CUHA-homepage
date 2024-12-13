package CUHA.homepage.controller;

import CUHA.homepage.security.dto.UserJoinResponse;
import CUHA.homepage.security.dto.UserjoinRequest;
import CUHA.homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/join")
    public UserJoinResponse join(@RequestBody UserjoinRequest user) {
        return userService.addUser(user);
    }
}

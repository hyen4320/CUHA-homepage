package CUHA.homepage.controller;

import CUHA.homepage.model.User;
import CUHA.homepage.security.dto.UserRUDRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/hello")
    public String login() {
        String m="ee";
        return m;
    }
    @PutMapping("/set/score")
    public User setScore(@RequestBody UserRUDRequest username) {
        return null;
    }
}

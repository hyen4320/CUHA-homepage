package CUHA.homepage.controller;

import CUHA.homepage.model.Gender;
import CUHA.homepage.security.dto.userDTO.*;
import CUHA.homepage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest user, HttpServletRequest request) {
        UserLoginResponse loginUser=userService.loginUser(user);
        if(loginUser.isSuccess()==true){
            request.getSession().setAttribute("user", user.getUsername());
            //session이름 jsessionid를 사용함
            return loginUser;
        }
        else{
            return loginUser;
        }
    }
    @PutMapping("/set/userInfo")
    public UserRUDResponse updateuser(@RequestBody UserjoinRequest username){
        return userService.updateUser(username);
    }

    @GetMapping("/gender")
    public Gender getGender(@RequestParam String user) {

        return userService.getGender(user);
    }
    @GetMapping("/score")
    public Long getScore(@RequestParam String user){
        return userService.getScore(user);
    }
}

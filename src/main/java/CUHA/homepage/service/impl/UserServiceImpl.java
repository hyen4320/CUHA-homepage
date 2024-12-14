package CUHA.homepage.service.impl;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.User;
import CUHA.homepage.model.UserRole;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.*;
import CUHA.homepage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserJoinResponse addUser(UserjoinRequest user) {
        User addUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .userRole(UserRole.user)
                .score(0L)
                .isActive(true)
                .gender(user.getGender())
                .build();
        userRepository.save(addUser);
        return UserJoinResponse.builder().build();
    }

    @Override
    public UserRUDResponse updateUser(UserjoinRequest user) {
        Optional<User> findUser= userRepository.findByUsername(user.getUsername());
        if(findUser.isEmpty()){
            return UserRUDResponse.builder().message("존재하지 않는 사용자입니다.").build();
        }
        User updateUser= findUser.get();
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setGender(user.getGender());
        userRepository.save(updateUser);
        return UserRUDResponse.builder().message("변경이 완료되었습니다.").build();
    }

    @Override
    public UserRUDResponse deleteUser(UserRUDRequest user) {
        return null;
    }

    @Override
    public UserFindResponse getUser(UserRUDRequest user) {
        Optional<User> findUser= userRepository.findByUsername(user.getUsername());
        if(findUser.isEmpty()){
            return UserFindResponse.builder().message("존재하지 않는 사용자입니다.").build();
        }
        User userInfo= findUser.get();
        UserFindResponse userFindResponse = UserFindResponse.builder()
                .username(userInfo.getUsername())
                .score(userInfo.getScore())
                .gender(userInfo.getGender())
                .message("유저 검색에 성공했습니다.").build();
        return userFindResponse;
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest user) {
        Optional<User> loginUser = userRepository.findByUsername(user.getUsername());
        if(loginUser.isEmpty()){
            return UserLoginResponse.builder()
                    .message("로그인 실패")
                    .success(false)
                    .build();
        }
        User userInfo= loginUser.get();
        if(userInfo.getPassword().equals(user.getPassword())&& userInfo.getUsername().equals(user.getUsername())){
            return UserLoginResponse.builder()
                    .message("로그인 성공")
                    .success(true)
                    .build();
        }
        else{
            return UserLoginResponse.builder()
                    .message("로그인 실패")
                    .success(false)
                    .build();
        }
    }

    @Override
    public List<UserFindResponse> getUsers() {
        //스프림으로 User->UserFindResponse로 바꿔주기
        List<UserFindResponse> result;
        List<User> streamUser= userRepository.findAll();
        streamUser.stream().map(x-> UserFindResponse.builder()
                .username(x.getUsername())
                .score(x.getScore())
                .gender(x.getGender())
                .userRole(x.getUserRole())
                .isActive(x.isActive())
                .created_at(x.getCreated_at())
                .build()).toList();

        return streamUser.stream().map(x-> UserFindResponse.builder()
                .username(x.getUsername())
                .score(x.getScore())
                .gender(x.getGender())
                .userRole(x.getUserRole())
                .isActive(x.isActive())
                .created_at(x.getCreated_at())
                .build()).toList();
    }

    @Override
    public UserRUDResponse deactivateUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<User> checkUser = userRepository.findByUsername(session.getAttribute("user").toString());
        if (checkUser.isEmpty()) {
            return UserRUDResponse.builder().message("존재하지 않는 사용자입니다.").build();
        }
        if ((checkUser.get().getUserRole() == UserRole.admin || checkUser.get().getUserRole() == UserRole.staff)) {
            checkUser.get().setActive(false);
        } else {
            return UserRUDResponse.builder().message("존재하지 않는 사용자입니다.").build();
        }
        return UserRUDResponse.builder().message("뭔가 중대한 에러").build();
    }

    @Override
    public UserRUDResponse activateUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<User> checkUser = userRepository.findByUsername(session.getAttribute("user").toString());
        if (checkUser.isEmpty()) {
            return UserRUDResponse.builder().message("존재하지 않는 사용자입니다.").build();
        }
        if ((checkUser.get().getUserRole() == UserRole.admin || checkUser.get().getUserRole() == UserRole.staff)) {
            checkUser.get().setActive(false);
        } else {
            return UserRUDResponse.builder().message("존재하지 않는 사용자입니다.").build();
        }
        return UserRUDResponse.builder().message("뭔가 중대한 에러").build();
    }

    @Override
    public Long getScore(String user) {
        Optional<User> findScore=userRepository.findByUsername(user);
        if(findScore.isEmpty()){
            throw new IllegalArgumentException("찾을 수 없는 사용자입니다ㅏ.");
        }
        System.out.println(findScore.get().getScore());
        return findScore.get().getScore();

    }

    @Override
    public UserRUDResponse setScore(UserRUDRequest user, Long score) {

        Optional<User> findScore=userRepository.findByUsername(user.getUsername());
        if(findScore.isEmpty()){
            throw new IllegalArgumentException("찾을 수 없는 사용자입니다ㅏ.");
        }
        findScore.get().setScore(score);
        return UserRUDResponse.builder().message("점수가 변경되었습니다.").build();
    }

    @Override
    public UserRUDResponse setUserRole(UserRUDRequest user, UserRole userRole,HttpServletRequest req) {
        Optional<User> checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser.isEmpty()) {
            return null;
        }
        if ((checkUser.get().getUserRole() == UserRole.admin || checkUser.get().getUserRole() == UserRole.staff)) {
            checkUser.get().setUserRole(userRole);
            return UserRUDResponse.builder().message("변경에 성공하였습니다.").build();
        } else {
            return null;
        }
    }

    @Override
    public UserRole getUserRole(UserRUDRequest user) {

        Optional<User> checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser.isEmpty()) {
            return null;
        }
        if ((checkUser.get().getUserRole() == UserRole.admin || checkUser.get().getUserRole() == UserRole.staff)) {
            checkUser.get().setActive(false);
            return checkUser.get().getUserRole();
        } else {
            return null;
        }
    }

    @Override
    public Gender getGender(String user) {
        Optional<User> findGender=userRepository.findByUsername(user);
        System.out.println(user);
        System.out.println(findGender);
        if(findGender.isEmpty()){
            throw new IllegalArgumentException("찾을 수 없는 사용자입니다ㅏ.");
        }
        System.out.println(findGender.get().getGender());
        return findGender.get().getGender();
    }

    @Override
    public UserRUDResponse setGender(UserRUDRequest user, Gender gender) {
        Optional<User> findScore=userRepository.findByUsername(user.getUsername());
        if(findScore.isEmpty()){
            throw new IllegalArgumentException("찾을 수 없는 사용자입니다ㅏ.");
        }
        findScore.get().setGender(gender);
        return UserRUDResponse.builder().message("성별이 변경되었습니다.").build();
    }
}
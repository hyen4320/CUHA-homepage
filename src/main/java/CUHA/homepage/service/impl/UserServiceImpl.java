package CUHA.homepage.service.impl;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.User;
import CUHA.homepage.model.UserRole;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.*;
import CUHA.homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return null;
    }

    @Override
    public List<UserFindResponse> getUsers() {
        return List.of();
    }

    @Override
    public UserRUDResponse deactivateUser(UserRUDRequest user) {
        return null;
    }

    @Override
    public UserRUDResponse activateUser(UserRUDRequest user) {
        return null;
    }

    @Override
    public Long getScore(UserRUDRequest user) {
        return 0L;
    }

    @Override
    public UserRUDResponse setScore(UserRUDRequest user, Long score) {
        return null;
    }

    @Override
    public UserRUDResponse setUserRole(UserRUDRequest user, UserRole userRole) {
        return null;
    }

    @Override
    public UserRole getUserRole(UserRUDRequest user) {
        return null;
    }

    @Override
    public Gender getGender(UserRUDRequest user) {
        return null;
    }

    @Override
    public Gender setGender(UserRUDRequest user, Gender gender) {
        return null;
    }
}
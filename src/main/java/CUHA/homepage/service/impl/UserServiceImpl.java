package CUHA.homepage.service.impl;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.User;
import CUHA.homepage.model.UserRole;
import CUHA.homepage.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {


    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public void deactivateUser(Long id) {

    }

    @Override
    public void activateUser(Long id) {

    }

    @Override
    public Long getScore(Long id) {
        return 0L;
    }

    @Override
    public void setScore(Long id, Long score) {

    }

    @Override
    public void setUserRole(Long id, UserRole userRole) {

    }

    @Override
    public UserRole getUserRole(Long id) {
        return null;
    }

    @Override
    public Gender getGender(Long id) {
        return null;
    }

    @Override
    public Gender setGender(Long id, Gender gender) {
        return null;
    }
}
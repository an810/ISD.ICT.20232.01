package com.aims.service;

import com.aims.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(String username, String password, String role);
    void deleteUser(String userId);
    User changePassword(String userId, String currentPassword, String newPassword);
    User updateUser(User newUser);
    List<User> getAllUsers();
    void login(String username, String password, String role);
}

package com.aims.service.impl;

import com.aims.entity.user.User;
import com.aims.exception.IncorrectPasswordException;
import com.aims.exception.UserNotFoundException;
import com.aims.repository.UserRepository;
import com.aims.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setBlockStatus(false);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User changePassword(String userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            if (user.getPassword().equals(currentPassword)) {
                user.setPassword(newPassword);
                return userRepository.save(user);
            } else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User updateUser(User newUser) {
        User user = userRepository.findById(newUser.getId()).orElse(null);
        if (user != null) {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setRole(newUser.getRole());
            user.setBlockStatus(newUser.getBlockStatus());
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return;
            } else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}

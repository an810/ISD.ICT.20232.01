package com.aims.controller;


import com.aims.entity.user.User;
import com.aims.service.UserService;
import com.aims.utils.Constants;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.login(username, password, role);
    }

    @PostMapping("/create")
    public User createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        return userService.createUser(username, password, role);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam String userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/change-password")
    public User changePassword(@RequestParam String userId, @RequestParam String currentPassword, @RequestParam String newPassword) {
        return userService.changePassword(userId, currentPassword, newPassword);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User newUser) {
        return userService.updateUser(newUser);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


}

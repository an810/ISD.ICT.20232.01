package com.aims.controller;


import com.aims.entity.response.AIMSResponse;
import com.aims.entity.user.User;
import com.aims.service.UserService;
import com.aims.utils.Constants;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AIMSResponse<Void>> login(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.login(username, password, role);
        AIMSResponse<Void> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Login successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<AIMSResponse<User>> createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        User user = userService.createUser(username, password, role);
        System.out.println("Create new user successfully");
        AIMSResponse<User> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Create new user successfully",user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AIMSResponse<Void>> deleteUser(@RequestParam String userId) {
        userService.deleteUser(userId);
        AIMSResponse<Void> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Delete user successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<AIMSResponse<User>> changePassword(@RequestParam String userId, @RequestParam String currentPassword, @RequestParam String newPassword) {
        User user = userService.changePassword(userId, currentPassword, newPassword);
        AIMSResponse<User> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Change password successfully", user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AIMSResponse<User>> updateUser(@PathVariable String id, @RequestBody User newUser) {
        User user = userService.updateUser(id, newUser);
        AIMSResponse<User> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Update user successfully", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<AIMSResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        AIMSResponse<List<User>> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Get all users successfully", users);
        return ResponseEntity.ok(response);
    }

}

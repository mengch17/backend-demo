package com.meng.backenddemo.controller;

import com.meng.backenddemo.dto.UserDto;
import com.meng.backenddemo.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.meng.backenddemo.response.UserResponse;

@RestController
@RequestMapping("v1/users")
public class UserControllerV1 {

    private static final Logger log = LogManager.getLogger(UserControllerV1.class);

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public ResponseEntity<UserResponse> findUserByUserUuid() {
        UserDto userDto = new UserDto();
        UserResponse userResponse = new UserResponse(200, "UserController Health Checked", userDto);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * find user by uuid
     * @param userUuid
     * @return
     */
    @GetMapping("/{userUuid}")
    public ResponseEntity<UserResponse> findUserByUserUuid(@PathVariable String userUuid) {
        try {
            UserDto userDto = userService.getUserByUuid(userUuid);
            UserResponse userResponse = new UserResponse(200, "User found successfully", userDto);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            UserResponse userResponse = new UserResponse(404, "User not found", null);
            return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto) {
        log.info("Creating new user");
        try {
            UserDto createdUser = userService.createUser(userDto);
            UserResponse userResponse = new UserResponse(201, "User created successfully", createdUser);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            UserResponse userResponse = new UserResponse(500, "Error creating user: " + e.getMessage(), null);
            return new ResponseEntity<>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{userUuid}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String userUuid) {
        try {
            userService.deleteUserByUuid(userUuid);
            UserResponse userResponse = new UserResponse(200, "User deleted successfully", null);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            UserResponse userResponse = new UserResponse(404, "User not found or error deleting user: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponse);
        }
    }
}

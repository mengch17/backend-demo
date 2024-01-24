package com.meng.backenddemo.controller;

import com.meng.backenddemo.dto.UserDto;
import com.meng.backenddemo.response.ApiResponse;
import com.meng.backenddemo.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v2/users")
public class UserControllerV2 {
    private static final Logger log = LogManager.getLogger(UserControllerV1.class);

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<UserDto>> healthCheck() {
        log.info("UserController Health Checked");
        return ResponseEntity.ok(ApiResponse.of(200, "UserController Health Checked", null));
    }

    @GetMapping("/{userUuid}")
    public ResponseEntity<ApiResponse<UserDto>> findUserByUserUuid(@PathVariable String userUuid) {
        try {
            UserDto userDto = userService.getUserByUuid(userUuid);
            return ResponseEntity.ok(ApiResponse.of(200, "User found successfully", userDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.messageOnly(404, "User not found"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto) {
        log.info("Creating new user");
        try {
            UserDto createdUser = userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.of(201, "User created successfully", createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.messageOnly(500, "Error creating user: " + e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{userUuid}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userUuid) {
        try {
            userService.deleteUserByUuid(userUuid);
            return ResponseEntity.ok(ApiResponse.messageOnly(200, "User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.messageOnly(404, "User not found or error deleting user: " + e.getMessage()));
        }
    }
}

package com.ashokit.UAM.controller;

import com.ashokit.UAM.dto.UserDTO;
import com.ashokit.UAM.exceptions.DuplicateUserException;
import com.ashokit.UAM.exceptions.UserNotFoundException;
import com.ashokit.UAM.model.UserEntity;
import com.ashokit.UAM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserEntity savedUser = userService.saveUserEntity(userDTO);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (DuplicateUserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            UserEntity user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

package com.ciam.cobain.controller;

import org.springframework.web.bind.annotation.*;

import com.ciam.cobain.dto.response.BaseResponse;
import com.ciam.cobain.entity.UserEntity;
import com.ciam.cobain.service.UserService;
import com.fasterxml.jackson.databind.JsonSerializable.Base;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public BaseResponse<List<UserEntity>> findAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public BaseResponse<Optional<UserEntity>> findUserById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public BaseResponse<UserEntity> saveUser(@RequestBody UserEntity employeeEntity) {
        return userService.saveUser(employeeEntity);
    }

    @PostMapping("/bulkcreate")
    public BaseResponse<UserEntity[]> saveUserWithHPA(@RequestBody UserEntity[] employeeEntity) {
        return userService.saveUserWithHPA(employeeEntity);
    }

    @PutMapping
    public BaseResponse<UserEntity> updateUser(@RequestBody UserEntity employeeEntity) {
        return userService.updateUser(employeeEntity);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<UserEntity> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }

}
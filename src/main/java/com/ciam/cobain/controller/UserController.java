package com.ciam.cobain.controller;

import org.springframework.web.bind.annotation.*;

import com.ciam.cobain.dto.BaseResponse;
import com.ciam.cobain.entity.UserEntity;
import com.ciam.cobain.service.UserService;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{id}")
    public BaseResponse<UserEntity> updateUser(@PathVariable("id") Integer id, @RequestBody UserEntity employeeEntity) {
        return userService.updateUser(id, employeeEntity);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<UserEntity> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }

}
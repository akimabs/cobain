package com.ciam.cobain.controller;

import org.springframework.web.bind.annotation.*;

import com.ciam.cobain.dto.request.UserRequest;
import com.ciam.cobain.dto.response.UserResponse;
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
    public List<UserEntity> findAllEmployee() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findEmployeeById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserEntity saveEmployee(@RequestBody UserEntity employeeEntity) {
        return userService.saveUser(employeeEntity);
    }

    @PutMapping
    public UserEntity updateEmployee(@RequestBody UserEntity employeeEntity) {
        return userService.updateUser(employeeEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }

    // Using Request and Response with save and update employee

    @PostMapping("/res")
    public UserResponse saveEmpResponse(@RequestBody UserRequest employeeRequest) {
        return userService.saveUser(employeeRequest);
    }

    @PutMapping("/res/{id}")
    public UserResponse updateEmpResponse(@RequestBody UserRequest employeeRequest, @PathVariable("id") Integer id) {
        return userService.updateUser(employeeRequest, id);
    }

}
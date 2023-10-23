package com.ciam.cobain.service;

import com.ciam.cobain.dto.request.UserRequest;
import com.ciam.cobain.dto.response.UserResponse;
import com.ciam.cobain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUser();

    Optional<UserEntity> findById(Integer id);

    UserEntity saveUser(UserEntity userEntity);

    UserEntity updateUser(UserEntity userEntity);

    void deleteUser(Integer id);

    // Using Request for Save and Update Employee
    UserResponse saveUser(UserRequest userRequest);

    UserResponse updateUser(UserRequest userRequest, Integer id);
}

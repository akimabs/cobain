package com.ciam.cobain.service;

import com.ciam.cobain.dto.response.BaseResponse;
import com.ciam.cobain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {

    BaseResponse<List<UserEntity>> findAllUser();

    BaseResponse<Optional<UserEntity>> findById(Integer id);

    BaseResponse<UserEntity> saveUser(UserEntity userEntity);

    BaseResponse<UserEntity[]> saveUserWithHPA(UserEntity[] userEntity);

    BaseResponse<UserEntity> updateUser(@PathVariable("id") Integer id, UserEntity userEntity);

    BaseResponse<UserEntity> deleteUser(Integer id);
}

package com.ciam.cobain.service.impl;

import com.ciam.cobain.dto.mapper.UserMapper;
import com.ciam.cobain.dto.request.UserRequest;
import com.ciam.cobain.dto.response.UserResponse;
import com.ciam.cobain.entity.UserEntity;
import com.ciam.cobain.repository.UserRepository;
import com.ciam.cobain.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Using Request and Response with save and update employee

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        UserEntity userEntity = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        userRepository.save(userEntity);
        return UserMapper.MAPPER.fromEntityToResponse(userEntity);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, Integer id) {

        Optional<UserEntity> checkExistingEmployee = findById(id);
        if (!checkExistingEmployee.isPresent())
            throw new RuntimeException("Employee Id " + id + " Not Found!");

        UserEntity userEntity = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        userEntity.setId(id);
        userRepository.save(userEntity);
        return UserMapper.MAPPER.fromEntityToResponse(userEntity);
    }
}

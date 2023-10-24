package com.ciam.cobain.service.impl;

import com.ciam.cobain.dto.response.BaseResponse;
import com.ciam.cobain.entity.UserEntity;
import com.ciam.cobain.repository.UserRepository;
import com.ciam.cobain.service.UserService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse<List<UserEntity>> findAllUser() {
        List<UserEntity> data = userRepository.findAll();
        return new BaseResponse<List<UserEntity>>(200, data);
    }

    @Override
    public BaseResponse<Optional<UserEntity>> findById(Integer id) {
        Optional<UserEntity> data = userRepository.findById(id);
        return new BaseResponse<Optional<UserEntity>>(200, data);
    }

    @Override
    public BaseResponse<UserEntity> saveUser(UserEntity userEntity) {
        UserEntity data = userRepository.save(userEntity);
        return new BaseResponse<UserEntity>(200, data);
    }

    @Override
    public BaseResponse<UserEntity[]> saveUserWithHPA(UserEntity[] userEntities) {
        List<UserEntity> savedEntities = new ArrayList<>();

        for (UserEntity user : userEntities) {
            UserEntity savedUser = userRepository.save(user);
            savedEntities.add(savedUser);
        }
        return new BaseResponse<UserEntity[]>(200, savedEntities.toArray(new UserEntity[0]));
    }

    @Override
    public BaseResponse<UserEntity> updateUser(UserEntity userEntity) {
        UserEntity data = userRepository.save(userEntity);
        return new BaseResponse<UserEntity>(200, data);
    }

    @Override
    public BaseResponse<UserEntity> deleteUser(Integer id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserEntity data = userOptional.get();
            userRepository.delete(data);
            return new BaseResponse<UserEntity>(200, data);
        } else {
            return new BaseResponse<UserEntity>(404, null);
        }
    }
}

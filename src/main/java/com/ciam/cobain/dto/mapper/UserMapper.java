package com.ciam.cobain.dto.mapper;

import com.ciam.cobain.dto.request.UserRequest;
import com.ciam.cobain.dto.response.UserResponse;
import com.ciam.cobain.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity fromRequestToEntity(UserRequest userRequest);

    UserResponse fromEntityToResponse(UserEntity employeeEntity);
}
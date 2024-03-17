package com.bugratasdemir.graduationproject.mapper;

import com.bugratasdemir.graduationproject.dto.UserDTO;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User convertToUser(UserSaveRequest request);
    @Mapping(target = "status", constant = "ACTIVE")
    UserDTO convertToUserDTO(User user);
    @Mapping(target = "id", ignore = true)
    void updateUserFields(@MappingTarget User user, UserUpdateRequest request);

}

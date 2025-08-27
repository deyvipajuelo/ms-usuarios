package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    @Mapping(source = "rolId", target = "role")
    User toUser(UserRequest userRequest);

    default Role mapRole(Long rolId) {
        return rolId == null ? null : new Role(rolId);
    }
}

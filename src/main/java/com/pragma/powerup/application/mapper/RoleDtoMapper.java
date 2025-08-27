package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.RoleDto;
import com.pragma.powerup.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {
    RoleDto toDto(Role role);
    Role toModel(RoleDto roleDto);
}

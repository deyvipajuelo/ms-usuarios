package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.RoleDto;
import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestMapperTest {
    private final UserRequestMapper userRequestMapper = Mappers.getMapper(UserRequestMapper.class);

    @Test
    void toUser_ShouldMapUserRequestToUser() {
        // Given
        RoleDto roleDto = new RoleDto(1L, "ROLE_USER");
        UserRequest userRequest = new UserRequest(
                "Juan",
                "Pérez",
                "12345678",
                "987654321",
                LocalDate.of(1990, 1, 1),
                "juan.perez@example.com",
                "password123",
                roleDto
        );

        // When
        User user = userRequestMapper.toUser(userRequest);

        // Then
        assertNotNull(user);
        assertEquals(userRequest.nombre(), user.getNombre());
        assertEquals(userRequest.apellido(), user.getApellido());
        assertEquals(userRequest.documentoDeIdentidad(), user.getDocumentoDeIdentidad());
        assertEquals(userRequest.celular(), user.getCelular());
        assertEquals(userRequest.fechaNacimiento(), user.getFechaNacimiento());
        assertEquals(userRequest.correo(), user.getCorreo());
        assertEquals(userRequest.rol().id(), user.getRole().getId());
        assertEquals(userRequest.rol().nombre(), user.getRole().getNombre());
    }
}
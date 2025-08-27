package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRequestMapperTest {
    private final UserRequestMapper userRequestMapper = Mappers.getMapper(UserRequestMapper.class);

    @Test
    void toUser_ShouldMapUserRequestToUser() {
        // Given
        UserRequest userRequest = new UserRequest(
                "Juan",
                "Pérez",
                "12345678",
                "987654321",
                "juan.perez@example.com",
                "password123",
                1L
        );

        // When
        User user = userRequestMapper.toUser(userRequest);

        // Then
        assertNotNull(user);
        assertEquals(userRequest.nombre(), user.getNombre());
        assertEquals(userRequest.apellido(), user.getApellido());
        assertEquals(userRequest.documentoDeIdentidad(), user.getDocumentoDeIdentidad());
        assertEquals(userRequest.celular(), user.getCelular());
        assertEquals(userRequest.correo(), user.getCorreo());
        assertEquals(userRequest.rolId(), user.getRole().getId());
    }

    @Test
    void shouldReturnNullRoleWhenRolIdIsNull() {
        // Arrange
        UserRequest request = new UserRequest(
                "Ana",
                "Lopez",
                "87654321",
                "+987654321",
                "ana@example.com",
                "clave456",
                null
        );

        // Act
        User user = userRequestMapper.toUser(request);

        // Assert
        assertThat(user).isNotNull();
        assertThat(user.getRole()).isNull();
    }

    @Test
    void mapRoleDirectlyShouldCreateRoleWithId() {
        // Act
        Role role = userRequestMapper.mapRole(5L);

        // Assert
        assertThat(role).isNotNull();
        assertThat(role.getId()).isEqualTo(5L);
    }

    @Test
    void mapRoleDirectlyShouldReturnNullWhenIdIsNull() {
        // Act
        Role role = userRequestMapper.mapRole(null);

        // Assert
        assertThat(role).isNull();
    }
}
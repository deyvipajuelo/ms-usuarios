package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Test
    void getRoleByName_ShouldReturnRole_WhenRoleExists() {
        // Given
        String roleName = "ROLE_ADMIN";
        Role expectedRole = new Role(1L, roleName);
        when(rolePersistencePort.getRole(roleName)).thenReturn(expectedRole);

        // When
        Role actualRole = roleUseCase.getRoleByName(roleName);

        // Then
        assertEquals(expectedRole, actualRole);
        verify(rolePersistencePort, times(1)).getRole(roleName);
    }
}
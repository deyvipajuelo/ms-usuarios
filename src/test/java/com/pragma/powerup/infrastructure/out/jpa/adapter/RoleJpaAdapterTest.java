package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.infrastructure.exception.RoleNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RoleJpaAdapterTest {
    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private RoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

    @Test
    void getRole_ShouldReturnRole_WhenRoleExists() {
        // Arrange
        String roleName = "ADMIN";
        RoleEntity roleEntity = new RoleEntity();
        Role role = new Role();

        when(roleRepository.findByNombreIgnoreCase(roleName)).thenReturn(Optional.of(roleEntity));
        when(roleEntityMapper.toRole(roleEntity)).thenReturn(role);

        // Act
        Role result = roleJpaAdapter.getRole(roleName);

        // Assert
        assertNotNull(result);
        assertEquals(role, result);
        verify(roleRepository, times(1)).findByNombreIgnoreCase(roleName);
        verify(roleEntityMapper, times(1)).toRole(roleEntity);
    }

    @Test
    void getRole_ShouldThrowException_WhenRoleDoesNotExist() {
        // Arrange
        String roleName = "NON_EXISTENT_ROLE";

        when(roleRepository.findByNombreIgnoreCase(roleName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RoleNotFoundException.class, () -> roleJpaAdapter.getRole(roleName));
        verify(roleRepository, times(1)).findByNombreIgnoreCase(roleName);
        verify(roleEntityMapper, never()).toRole(any());
    }
}
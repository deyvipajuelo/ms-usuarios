package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.OwnerRequest;
import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.mapper.OwnerRequestMapper;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserHandlerTest {
    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private OwnerRequestMapper ownerRequestMapper;

    @Mock
    private IRoleServicePort roleServicePort;

    @InjectMocks
    private UserHandler userHandler;

    @Test
    void saveOwner_ShouldMapAndSaveOwner() {
        // Arrange
        OwnerRequest ownerRequest = mock(OwnerRequest.class);
        User user = new User();
        Role role = new Role(1L, "PROPIETARIO");

        when(ownerRequestMapper.toUser(ownerRequest)).thenReturn(user);
        when(roleServicePort.getRoleByName("PROPIETARIO")).thenReturn(role);

        // Act
        userHandler.saveOwner(ownerRequest);

        // Assert
        verify(ownerRequestMapper, times(1)).toUser(ownerRequest);
        verify(roleServicePort, times(1)).getRoleByName("PROPIETARIO");
        verify(userServicePort, times(1)).saveUser(user);
    }

    @Test
    void saveUser_ShouldMapAndSaveUser() {
        // Arrange
        UserRequest userRequest = mock(UserRequest.class);
        User user = new User();

        when(userRequestMapper.toUser(userRequest)).thenReturn(user);

        // Act
        userHandler.saveUser(userRequest);

        // Assert
        verify(userRequestMapper, times(1)).toUser(userRequest);
        verify(userServicePort, times(1)).saveUser(user);
    }

}
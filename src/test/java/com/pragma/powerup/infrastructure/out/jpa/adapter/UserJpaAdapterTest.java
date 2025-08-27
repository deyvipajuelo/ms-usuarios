package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.pragma.powerup.domain.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserJpaAdapterTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Test
    void saveUser_ShouldThrowException_WhenUserAlreadyExists() {
        // Arrange
        User user = new User();
        user.setDocumentoDeIdentidad("12345678");

        when(userRepository.findByDocumentoDeIdentidad(user.getDocumentoDeIdentidad()))
                .thenReturn(Optional.of(new UserEntity()));

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userJpaAdapter.saveUser(user));
        verify(userRepository, times(1)).findByDocumentoDeIdentidad(user.getDocumentoDeIdentidad());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void saveUser_ShouldSaveUser_WhenUserDoesNotExist() {
        // Arrange
        User user = new User();
        user.setDocumentoDeIdentidad("12345678");
        user.setClave("password");

        UserEntity userEntity = new UserEntity();
        when(userRepository.findByDocumentoDeIdentidad(user.getDocumentoDeIdentidad()))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getClave())).thenReturn("encodedPassword");
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        // Act
        userJpaAdapter.saveUser(user);

        // Assert
        verify(userRepository, times(1)).findByDocumentoDeIdentidad(user.getDocumentoDeIdentidad());
        verify(passwordEncoder, times(1)).encode("password");
        verify(userEntityMapper, times(1)).toEntity(user);
        verify(userRepository, times(1)).save(userEntity);
    }
}
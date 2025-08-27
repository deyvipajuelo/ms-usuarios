package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFechaNacimiento(LocalDate.of(2015, 1, 1));
    }



    @Test
    void saveUser_ShouldCallPersistencePort_WhenUserIsAdult() {
        // Arrange
        user.setFechaNacimiento(LocalDate.of(2000, 1, 1)); // Usuario mayor de edad

        // Act
        userUseCase.saveUser(user);

        // Assert
        verify(userPersistencePort, times(1)).saveUser(user);
    }

}
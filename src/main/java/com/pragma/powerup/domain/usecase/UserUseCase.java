package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.UserUnderAgeException;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        if (user.getFechaNacimiento().plusYears(18).isAfter(LocalDate.now())) {
            throw new UserUnderAgeException();
        }
        userPersistencePort.saveUser(user);
    }
}

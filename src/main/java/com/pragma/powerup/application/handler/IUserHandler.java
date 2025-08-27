package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.OwnerRequest;
import com.pragma.powerup.application.dto.UserRequest;

public interface IUserHandler {
    void saveOwner(OwnerRequest ownerRequest);
//    void saveUser(UserRequest userRequest);
}

package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.OwnerRequest;
import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.mapper.OwnerRequestMapper;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final OwnerRequestMapper ownerRequestMapper;
    private final IRoleServicePort roleServicePort;

    @Override
    public void saveOwner(OwnerRequest ownerRequest) {
        User user = ownerRequestMapper.toUser(ownerRequest);
        user.setRole(roleServicePort.getRoleByName("PROPIETARIO"));
        userServicePort.saveUser(user);
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);
        userServicePort.saveUser(user);
    }
}

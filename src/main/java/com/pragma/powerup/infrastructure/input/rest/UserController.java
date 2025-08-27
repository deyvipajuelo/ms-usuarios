package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.OwnerRequest;
import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserHandler userHandler;

    @PostMapping("/propietarios")
    public ResponseEntity<Void> saveOwner(@RequestBody @Valid OwnerRequest ownerRequest) {
        userHandler.saveOwner(ownerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/empleados")
    public ResponseEntity<Void> saveEmployee(@RequestBody @Valid UserRequest userRequest) {
        userHandler.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/clientes")
    public ResponseEntity<Void> saveCustomer(@RequestBody @Valid UserRequest userRequest) {
        userHandler.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

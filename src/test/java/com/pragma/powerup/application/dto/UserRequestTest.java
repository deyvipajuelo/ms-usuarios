package com.pragma.powerup.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserRequestTest {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private UserRequest buildValidUserRequest() {
        return new UserRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+123456789",
                "juan@example.com",
                "clave123",
                1L
        );
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        UserRequest request = buildValidUserRequest();

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenNombreIsBlank() {
        UserRequest request = new UserRequest(
                "   ",
                "Pérez",
                "12345678",
                "+123456789",
                "juan@example.com",
                "clave123",
                1L
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El nombre es obligatorio"));
    }

    @Test
    void shouldFailWhenDocumentoContainsLetters() {
        UserRequest request = new UserRequest(
                "Juan",
                "Pérez",
                "ABC1234",
                "+123456789",
                "juan@example.com",
                "clave123",
                1L
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El documento de identidad solo puede contener números"));
    }

    @Test
    void shouldFailWhenCelularIsInvalid() {
        UserRequest request = new UserRequest(
                "Juan",
                "Pérez",
                "12345678",
                "123-ABC",
                "juan@example.com",
                "clave123",
                1L
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El celular solo puede contener números, con máximo 13 caracteres"));
    }

    @Test
    void shouldFailWhenCorreoIsInvalid() {
        UserRequest request = new UserRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+123456789",
                "correo-invalido",
                "clave123",
                1L
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El correo no tiene un formato válido"));
    }

    @Test
    void shouldFailWhenRolIdIsNull() {
        UserRequest request = new UserRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+123456789",
                "juan@example.com",
                "clave123",
                null // inválido
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El rol es obligatorio"));
    }
}
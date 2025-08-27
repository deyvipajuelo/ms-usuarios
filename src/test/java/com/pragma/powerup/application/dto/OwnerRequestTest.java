package com.pragma.powerup.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerRequestTest {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private OwnerRequest buildValidOwnerRequest() {
        return new OwnerRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+123456789",
                LocalDate.of(1990, 5, 20),
                "juan@example.com",
                "clave123"
        );
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        OwnerRequest request = buildValidOwnerRequest();

        Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenNombreIsBlank() {
        OwnerRequest request = new OwnerRequest(
                "   ",
                "Pérez",
                "12345678",
                "+123456789",
                LocalDate.of(1990, 5, 20),
                "juan@example.com",
                "clave123"
        );

        Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El nombre es obligatorio"));
    }

    @Test
    void shouldFailWhenDocumentoContainsLetters() {
        OwnerRequest request = new OwnerRequest(
                "Juan",
                "Pérez",
                "A12345",
                "+123456789",
                LocalDate.of(1990, 5, 20),
                "juan@example.com",
                "clave123"
        );

        Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El documento de identidad solo puede contener números"));
    }

    @Test
    void shouldFailWhenCelularIsInvalid() {
        OwnerRequest request = new OwnerRequest(
                "Juan",
                "Pérez",
                "12345678",
                "123-ABC",
                LocalDate.of(1990, 5, 20),
                "juan@example.com",
                "clave123"
        );

        Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El celular solo puede contener números, con máximo 13 caracteres"));
    }

    @Test
    void shouldFailWhenFechaNacimientoIsNull() {
        OwnerRequest request = new OwnerRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+123456789",
                null,
                "juan@example.com",
                "clave123"
        );

        Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("La fecha de nacimiento es obligatorio"));
    }

    @Test
    void shouldFailWhenCorreoIsInvalid() {
        OwnerRequest request = new OwnerRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+123456789",
                LocalDate.of(1990, 5, 20),
                "correo-invalido",
                "clave123"
        );

        Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("El correo no tiene un formato válido"));
    }

    @Test
    void shouldFailWhenClaveIsBlank() {
        OwnerRequest request = new OwnerRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+123456789",
                LocalDate.of(1990, 5, 20),
                "juan@example.com",
                "   "
        );

        Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(request);

        assertThat(violations).anyMatch(v -> v.getMessage().equals("La clave es obligatorio"));
    }
}
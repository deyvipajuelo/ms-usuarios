package com.pragma.powerup.application.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record OwnerRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El documento de identidad es obligatorio")
        String documentoDeIdentidad,

        @NotBlank(message = "El celular es obligatorio")
        @Pattern(
                regexp = "^\\+?[0-9]{1,13}$",
                message = "El celular solo puede contener números, con máximo 13 caracteres"
        )
        String celular,

        @NotNull(message = "La fecha de nacimiento es obligatorio")
        LocalDate fechaNacimiento,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String correo,

        @NotBlank(message = "La clave es obligatorio")
        String clave
) {

}

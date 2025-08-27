package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.OwnerRequest;
import com.pragma.powerup.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class OwnerRequestMapperTest {
    private final OwnerRequestMapper mapper = Mappers.getMapper(OwnerRequestMapper.class);

    @Test
    void toUser_ShouldMapOwnerRequestToUser() {
        // Arrange
        OwnerRequest ownerRequest = new OwnerRequest(
                "Juan",
                "Pérez",
                "12345678",
                "+1234567890",
                java.time.LocalDate.of(1990, 1, 1),
                "correo@valido.com",
                "claveSegura"
        );

        // Act
        User user = mapper.toUser(ownerRequest);

        // Assert
        assertEquals(ownerRequest.nombre(), user.getNombre());
        assertEquals(ownerRequest.apellido(), user.getApellido());
        assertEquals(ownerRequest.documentoDeIdentidad(), user.getDocumentoDeIdentidad());
        assertEquals(ownerRequest.celular(), user.getCelular());
        assertEquals(ownerRequest.fechaNacimiento(), user.getFechaNacimiento());
        assertEquals(ownerRequest.correo(), user.getCorreo());
        assertEquals(ownerRequest.clave(), user.getClave());
    }
}
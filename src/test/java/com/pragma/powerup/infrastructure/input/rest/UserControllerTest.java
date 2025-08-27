package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.OwnerRequest;
import com.pragma.powerup.application.handler.IUserHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IUserHandler userHandler;

    @Test
    void saveOwner_ReturnsCreated_WhenRequestIsValid() throws Exception {
        String json = """
            {
                "nombre": "Juan",
                "apellido": "Pérez",
                "documentoDeIdentidad": "12345678",
                "celular": "3001234567",
                "fechaNacimiento": "1990-01-01",
                "correo": "juan@example.com",
                "clave": "secreta"
            }
        """;

        mockMvc.perform(post("/user/propietarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(userHandler).saveOwner(any(OwnerRequest.class));
    }

    @Test
    void saveOwner_ReturnsBadRequest_WhenInvalidRequest() throws Exception {
        String invalidJson = """
            {
                "nombre": "",
                "apellido": "Pérez"
            }
        """;

        mockMvc.perform(post("/user/propietarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}
package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(name = "documento_de_identidad", unique = true)
    private String documentoDeIdentidad;
    private String celular;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String correo;
    private String clave;
    @Column(name = "rol_id")
    private Long rolId;
}

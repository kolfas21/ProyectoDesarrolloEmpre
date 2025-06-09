package com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user") // <-- Especifica el nombre correcto de la columna
    private Long idUser;

    private String nombre;
    private String cedula;
    private String correo;
    private String contrasena;
    private String rol;
}
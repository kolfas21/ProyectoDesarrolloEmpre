package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteDTO {
    private Long idUsuario; // 🔥 Nuevo: ID del usuario con rol PACIENTE

    // 🔹 Datos personales básicos que vienen desde el registro
    private String nombre;         // (solo lectura en frontend)
    private String correo;         // (solo lectura en frontend)
    private String cedula;         // (solo lectura en frontend)
    private LocalDate fechaNacimiento; // (lectura / opcional)
    private String contrasena;

    // 🔸 Datos clínicos que el médico debe llenar
    private String sexo;
    private String estadoCivil;
    private String ocupacion;
    private String actividadFisica;
    private Double peso;
    private Double estatura;
    private String alergias;
    private String sintomas;
    
}

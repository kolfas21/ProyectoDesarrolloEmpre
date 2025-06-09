package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import lombok.Data;

@Data
public class PacienteDTO {
    private String nombre;
    private String cedula;
    private String correo;
    private String contrasena;
    private String sexo;
    private String estadoCivil;
    private String ocupacion;
    private String actividadFisica;
    private Double peso;
    private Double estatura;
    private String fechaNacimiento;
}
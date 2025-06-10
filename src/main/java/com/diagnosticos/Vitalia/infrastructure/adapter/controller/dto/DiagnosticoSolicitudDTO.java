package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO usado para recibir datos del front con info del paciente y síntomas.
 * Forma parte de la capa de infraestructura.
 */
@Data
public class DiagnosticoSolicitudDTO {
    private String id;   // Úsalo si se lo envían desde el front
    private String nombre;
    private int edad;
    private String genero;
    private List<String> sintomas;
}
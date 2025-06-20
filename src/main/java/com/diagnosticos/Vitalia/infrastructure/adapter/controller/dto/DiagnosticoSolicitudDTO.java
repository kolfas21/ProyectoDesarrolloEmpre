package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import lombok.Data;
import java.util.List;

@Data
public class DiagnosticoSolicitudDTO {
    private Long idConsulta;
    private List<String> sintomas;
    private Long id;
    private String nombre;
    private Integer edad;
    private String genero;
}

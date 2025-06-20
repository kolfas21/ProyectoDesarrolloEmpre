package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import lombok.Data;
import java.util.Map;

@Data
public class DiagnosticoRespuestaDTO {
    private Map<String, Object> data;
    private String rawJson;
}


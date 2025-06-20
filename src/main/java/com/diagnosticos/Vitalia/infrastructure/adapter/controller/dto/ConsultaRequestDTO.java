// ConsultaRequestDTO.java
package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ConsultaRequestDTO {
    private Long idPaciente; // Este es el ID del UserEntity con rol PACIENTE
    private Long idMedico;   // Este es el ID del MedicoEntity
    private LocalDateTime fechaHoraConsulta;
}

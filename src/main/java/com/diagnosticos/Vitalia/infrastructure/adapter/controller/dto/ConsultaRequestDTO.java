<<<<<<< HEAD
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
=======
package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import lombok.Data;

@Data
public class ConsultaRequestDTO {
    private Long idPaciente;
    private Long idMedico;
    // Agrega aquí otros campos necesarios para la consulta
}
>>>>>>> 39cab2f1dfea6e39219611a2c640b3b247bcb829

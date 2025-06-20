<<<<<<< HEAD
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
=======
package com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto;

import lombok.Data;
import java.util.List;

@Data
public class DiagnosticoSolicitudDTO {
    private Long idConsulta;
    private String id;
    private String nombre;
    private int edad;
    private String genero;
    private List<String> sintomas;
}
>>>>>>> 39cab2f1dfea6e39219611a2c640b3b247bcb829

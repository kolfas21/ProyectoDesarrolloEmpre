<<<<<<< HEAD
package com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta_medica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long idConsulta;

    @Column(name = "fecha_hora_consulta", nullable = false)
    private LocalDateTime fechaHoraConsulta;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos = 15;

    // ✅ ÚNICA relación válida para el paciente (ya no necesitas UserEntity aquí)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private MedicoEntity medico;

    @Column(name = "estado", nullable = false)
    private String estado = "PENDIENTE";
}
=======
package com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "consulta_medica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long idConsulta;

    @Column(name = "fecha_consulta", nullable = false)
    private LocalDate fechaConsulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private MedicoEntity medico;
}


>>>>>>> 39cab2f1dfea6e39219611a2c640b3b247bcb829

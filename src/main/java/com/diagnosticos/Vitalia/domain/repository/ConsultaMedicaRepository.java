package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.ConsultaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedicaEntity, Long> {

    @Query(
    value = """
        SELECT COUNT(*) > 0
        FROM consulta_medica c
        WHERE c.id_medico = :medicoId
          AND c.fecha_hora_consulta < :fin
          AND (c.fecha_hora_consulta + (c.duracion_minutos || ' minutes')::interval) > :inicio
        """,
        nativeQuery = true
    )
    boolean existeTraslape(
        @Param("medicoId") Long medicoId,
        @Param("inicio") LocalDateTime inicio,
        @Param("fin") LocalDateTime fin
    );
}

<<<<<<< HEAD
package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.ConsultaMedicaEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.MedicoEntity;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedicaEntity, Long> {
    boolean existsByMedicoAndFechaHoraConsultaBetween(MedicoEntity medico, LocalDateTime inicio, LocalDateTime fin);


}
=======
package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.ConsultaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedicaEntity, Long> {
}
>>>>>>> 39cab2f1dfea6e39219611a2c640b3b247bcb829

<<<<<<< HEAD
package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.PacienteEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    // Si necesitas métodos personalizados, puedes agregarlos aquí
    Optional<PacienteEntity> findByUser(UserEntity user);
=======
package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    // Si necesitas métodos personalizados, puedes agregarlos aquí
>>>>>>> 39cab2f1dfea6e39219611a2c640b3b247bcb829
}
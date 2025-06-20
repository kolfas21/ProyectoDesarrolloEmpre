<<<<<<< HEAD
package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Optional<MedicoEntity> findById(Long id);
    void deleteById(Long id);
    List<MedicoEntity> findAll();
    Optional<MedicoEntity> findByUserCedula(String cedula);

=======
package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Optional<MedicoEntity> findById(Long id);
    void deleteById(Long id);
    List<MedicoEntity> findAll();
<<<<<<< HEAD
=======
    Optional<MedicoEntity> findByUserCedula(String cedula);

>>>>>>> f2a8c313a0fa67ffbb6d6a505114cd5030a51751
>>>>>>> 39cab2f1dfea6e39219611a2c640b3b247bcb829
}
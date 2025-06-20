package com.diagnosticos.Vitalia.domain.repository;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByCorreo(String correo);
    boolean existsByCedula(String cedula);
    UserEntity findByCorreo(String correo);
    Optional<UserEntity> findByCedulaAndRol(String cedula, String rol);   

}
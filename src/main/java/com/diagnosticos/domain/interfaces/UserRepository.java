package com.diagnosticos.domain.interfaces;

import com.diagnosticos.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Ejemplo de consultas personalizadas si las necesitas
    boolean existsByCorreo(String correo);
    boolean existsByCedula(Long cedula);
}
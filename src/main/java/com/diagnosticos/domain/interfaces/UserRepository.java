package com.diagnosticos.domain.interfaces;

import com.diagnosticos.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Aquí puedes agregar consultas personalizadas si quieres
}

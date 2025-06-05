package com.diagnosticos.aplication.services;

import com.diagnosticos.domain.interfaces.UserRepository;
import com.diagnosticos.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Crear un usuario con rol por defecto "paciente"
    public User createUser(User user) {
        user.setRol("paciente");  // Forzamos rol predeterminado
        return userRepository.save(user);
    }

    // Crear múltiples usuarios
    public List<User> createUsers(List<User> users) {
        users.forEach(u -> u.setRol("paciente"));
        return userRepository.saveAll(users);
    }

    // Buscar usuario por UUID
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    // Obtener todos los usuarios
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Actualizar usuario
    public Optional<User> updateUser(UUID id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setNombre(userDetails.getNombre());
            user.setCorreo(userDetails.getCorreo());
            user.setCedula(userDetails.getCedula());
            user.setContrasena(userDetails.getContrasena());
            return userRepository.save(user);
        });
    }

    // Eliminar usuario por UUID
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}

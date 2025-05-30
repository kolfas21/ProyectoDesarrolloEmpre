package com.diagnosticos.aplication.services;

import com.diagnosticos.domain.interfaces.UserRepository;
import com.diagnosticos.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Crear un usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Crear múltiples usuarios
    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    // Buscar usuario por ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Obtener todos los usuarios
    public List<User> getAll() {
        return userRepository.findAll();
    }
}

package com.diagnosticos.api.controllers;

// Cambia la ruta del import según tu estructura real de paquetes
import com.diagnosticos.aplication.services.UserService;

import com.diagnosticos.domain.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Crear un usuario
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el usuario: " + e.getMessage());
        }
    }

    // Crear múltiples usuarios
    @PostMapping("/bulk")
    public ResponseEntity<?> createUsers(@RequestBody List<@Valid User> users) {
        try {
            List<User> createdUsers = userService.createUsers(users);
            return ResponseEntity.ok(createdUsers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear usuarios: " + e.getMessage());
        }
    }

    // Obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id) {
        // Convierte UUID a Long (usa la parte más baja del UUID como ejemplo)
        Long longId = id.getMostSignificantBits() & Long.MAX_VALUE;
        return userService.findById(longId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
}

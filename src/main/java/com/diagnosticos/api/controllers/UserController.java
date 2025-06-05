package com.diagnosticos.api.controllers;

import com.diagnosticos.aplication.services.UserService;
import com.diagnosticos.domain.models.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Obtener usuario por ID (UUID)
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    // Actualizar usuario por ID (UUID)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails)
                .map(updatedUser -> ResponseEntity.ok().body(updatedUser))
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar usuario por ID (UUID)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar usuario: " + e.getMessage());
        }
    }
}

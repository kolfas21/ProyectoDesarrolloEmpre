package com.diagnosticos.Vitalia.infrastructure.adapter.controller;

import com.diagnosticos.Vitalia.application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.RegistroPacienteDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.ActualizarInfoMedicaDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, String>> registrarPaciente(@RequestBody RegistroPacienteDTO dto) {
        userService.registrarPaciente(dto);
        return ResponseEntity.ok(Map.of("mensaje", "Paciente registrado correctamente"));
    }

    @PutMapping("/{id}/info-medica")
    public ResponseEntity<String> actualizarInfoMedica(@PathVariable Long id, @RequestBody ActualizarInfoMedicaDTO dto) {
        userService.actualizarInfoMedica(id, dto);
        return ResponseEntity.ok("Información médica del paciente actualizada");
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> obtenerTodosLosUsuarios() {
        List<UserEntity> usuarios = userService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
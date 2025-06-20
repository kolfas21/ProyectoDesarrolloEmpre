package com.diagnosticos.Vitalia.infrastructure.adapter.controller;

import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.PacienteDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.MedicoDTO;

import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.UserEntity;
import com.diagnosticos.Vitalia.domain.repository.UserRepository;
import com.diagnosticos.Vitalia.application.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Necesitarás agregar BCryptPasswordEncoder bean
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

@RestController
@RequestMapping("/api/registro")
@RequiredArgsConstructor
public class RegistroController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final MedicoService medicoService; // Agregado MedicoService

    @PostMapping("/paciente")
public ResponseEntity<String> registrarPaciente(@RequestBody PacienteDTO dto) {
    if (userRepo.existsByCorreo(dto.getCorreo())) {
        return ResponseEntity.badRequest().body("❌ Correo ya registrado");
    }
    if (userRepo.existsByCedula(dto.getCedula())) {
        return ResponseEntity.badRequest().body("❌ Cédula ya registrada");
    }

    UserEntity user = new UserEntity();
    user.setNombre(dto.getNombre());
    user.setCedula(dto.getCedula());
    user.setCorreo(dto.getCorreo());
    user.setContrasena(passwordEncoder.encode(dto.getContrasena()));
    user.setFechaNacimiento(dto.getFechaNacimiento());
    user.setRol("PACIENTE");

    userRepo.save(user);

    return ResponseEntity.ok("✅ Usuario PACIENTE registrado correctamente");
}


    @PostMapping("/medico")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registrarMedico(@RequestBody MedicoDTO dto) {
        try {
            medicoService.registrarMedico(dto);
            return ResponseEntity.ok(Map.of("mensaje", "Médico registrado correctamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
package com.diagnosticos.Vitalia.infrastructure.adapter.controller;

import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.PacienteDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.MedicoDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.MedicoEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.PacienteEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.UserEntity;
import com.diagnosticos.Vitalia.domain.repository.MedicoRepository;
import com.diagnosticos.Vitalia.domain.repository.PacienteRepository;
import com.diagnosticos.Vitalia.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Necesitarás agregar BCryptPasswordEncoder bean
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registro")
@RequiredArgsConstructor
public class RegistroController {

    private final UserRepository userRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final PasswordEncoder passwordEncoder; 

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
        user.setFechaNacimiento(java.time.LocalDate.parse(dto.getFechaNacimiento()));
        // Si tienes campo de tipo/rol
        // user.setTipo("PACIENTE"); 

        user = userRepo.save(user);

        PacienteEntity paciente = new PacienteEntity();
        paciente.setUser(user);
        paciente.setFechaNacimiento(java.time.LocalDate.parse(dto.getFechaNacimiento()));
        paciente.setSexo(dto.getSexo());
        paciente.setEstadoCivil(dto.getEstadoCivil());
        paciente.setOcupacion(dto.getOcupacion());
        paciente.setActividadFisica(dto.getActividadFisica());
        paciente.setPeso(dto.getPeso());
        paciente.setEstatura(dto.getEstatura());

        pacienteRepo.save(paciente);

        return ResponseEntity.ok("✅ Paciente registrado correctamente");
    }

    @PostMapping("/medico")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registrarMedico(@RequestBody MedicoDTO dto) {
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
        // user.setTipo("MEDICO"); 
        user = userRepo.save(user);

        MedicoEntity medico = new MedicoEntity();
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setRegistroProfesional(dto.getRegistroProfesional());
        medico.setUser(user);

        medicoRepo.save(medico);

        return ResponseEntity.ok("✅ Médico registrado correctamente");
    }
}
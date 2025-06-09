package com.diagnosticos.Vitalia.infrastructure.adapter.controller;

import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.ConsultaRequestDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.ConsultaMedicaEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.MedicoEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.PacienteEntity;
import com.diagnosticos.Vitalia.domain.repository.ConsultaMedicaRepository;
import com.diagnosticos.Vitalia.domain.repository.MedicoRepository;
import com.diagnosticos.Vitalia.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/consulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final ConsultaMedicaRepository consultaRepo;

    @PostMapping
    public ResponseEntity<?> crearConsulta(@RequestBody ConsultaRequestDTO dto) {
        PacienteEntity paciente = pacienteRepo.findById(dto.getIdPaciente())
                .orElse(null);
        if (paciente == null) {
            return ResponseEntity.badRequest().body("Paciente no encontrado");
        }

        MedicoEntity medico = medicoRepo.findById(dto.getIdMedico())
                .orElse(null);
        if (medico == null) {
            return ResponseEntity.badRequest().body("Médico no encontrado");
        }

        ConsultaMedicaEntity consulta = new ConsultaMedicaEntity(null, LocalDate.now(), paciente, medico);
        consultaRepo.save(consulta);

        return ResponseEntity.ok("Consulta creada correctamente con ID: " + consulta.getIdConsulta());
    }
}
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registro")
@RequiredArgsConstructor
public class RegistroController {

    private final UserRepository userRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    /**
     * Registra un nuevo paciente con los datos básicos y clínicos.
     * @param dto datos del paciente recibidos desde el frontend.
     * @return mensaje de confirmación.
     */
    @PostMapping("/paciente")
    public ResponseEntity<String> registrarPaciente(@RequestBody PacienteDTO dto) {
        UserEntity user = new UserEntity(null,
                dto.getNombre(),
                dto.getCedula(),
                dto.getCorreo(),
                dto.getContrasena(),
                "PACIENTE");

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

    /**
     * Registra un nuevo médico con sus datos personales y profesionales.
     * @param dto datos del médico recibidos desde el frontend.
     * @return mensaje de confirmación.
     */
    @PostMapping("/medico")
    public ResponseEntity<String> registrarMedico(@RequestBody MedicoDTO dto) {
        UserEntity user = new UserEntity(null,
                dto.getNombre(),
                dto.getCedula(),
                dto.getCorreo(),
                dto.getContrasena(),
                "MEDICO");

        user = userRepo.save(user);

        MedicoEntity medico = new MedicoEntity(null,
                dto.getEspecialidad(),
                dto.getRegistroProfesional(),
                user);

        medicoRepo.save(medico);

        return ResponseEntity.ok("✅ Médico registrado correctamente");
    }
}


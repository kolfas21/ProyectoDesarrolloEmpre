package com.diagnosticos.Vitalia.application.service;

import com.diagnosticos.Vitalia.domain.repository.PacienteRepository;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.PacienteEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.UserEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.RegistroPacienteDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.ActualizarInfoMedicaDTO;
import com.diagnosticos.Vitalia.domain.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registrarPaciente(@NotNull RegistroPacienteDTO dto) {
        UserEntity usuario = new UserEntity();
        usuario.setNombre(dto.getNombre());
        usuario.setCedula(dto.getCedula());
        usuario.setCorreo(dto.getCorreo());
        // Encriptar la contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        if (dto.getFechaNacimiento() != null) {
            usuario.setFechaNacimiento(dto.getFechaNacimiento());
        }
        // Asignar el rol automáticamente
        usuario.setRol("PACIENTE");
        return userRepository.save(usuario);
    }

    @Transactional
    public void actualizarInfoMedica(Long id, ActualizarInfoMedicaDTO dto) {
        Optional<PacienteEntity> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            PacienteEntity paciente = optionalPaciente.get();
            paciente.setPeso(dto.getPeso());
            paciente.setEstatura(dto.getEstatura());
            paciente.setActividadFisica(dto.getActividadFisica());
            paciente.setAlergias(dto.getAlergias());
            paciente.setSintomas(dto.getSintomas());
            pacienteRepository.save(paciente); // Guardar la información actualizada
        } else {
            throw new RuntimeException("Paciente no encontrado con id: " + id);
        }
    }
    public List<UserEntity> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }
}
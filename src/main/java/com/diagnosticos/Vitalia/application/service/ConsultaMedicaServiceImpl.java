package com.diagnosticos.Vitalia.application.service;

import com.diagnosticos.Vitalia.domain.repository.ConsultaMedicaRepository;
import com.diagnosticos.Vitalia.domain.repository.MedicoRepository;
import com.diagnosticos.Vitalia.domain.repository.PacienteRepository;
import com.diagnosticos.Vitalia.domain.repository.UserRepository;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.ConsultaRequestDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.ConsultaMedicaEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.MedicoEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.PacienteEntity;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaMedicaServiceImpl implements ConsultaMedicaService {

    private final ConsultaMedicaRepository consultaRepo;
    private final UserRepository userRepo;
    private final MedicoRepository medicoRepo;
    private final PacienteRepository pacienteRepo;

    @Override
    public ConsultaMedicaEntity crearConsulta(ConsultaRequestDTO dto) {
        // Validar existencia del médico
        MedicoEntity medico = medicoRepo.findById(dto.getIdMedico())
                .orElseThrow(() -> new RuntimeException("❌ Médico no encontrado con ID: " + dto.getIdMedico()));

        // Validar que el usuario sea un paciente
        UserEntity userPaciente = userRepo.findById(dto.getIdPaciente())
                .filter(u -> "PACIENTE".equalsIgnoreCase(u.getRol()))
                .orElseThrow(() -> new RuntimeException("❌ ID no corresponde a un paciente válido: " + dto.getIdPaciente()));

        // Obtener PacienteEntity asociado al usuario
        PacienteEntity pacienteEntity = pacienteRepo.findByUser(userPaciente)
                .orElseThrow(() -> new RuntimeException("❌ No se encontró el paciente con ID de usuario: " + dto.getIdPaciente()));

        // Calcular fecha de la consulta
        LocalDateTime fechaConsulta = calcularSiguienteHoraDisponible(medico);

        // Crear y guardar la consulta
        ConsultaMedicaEntity consulta = new ConsultaMedicaEntity();
        consulta.setFechaHoraConsulta(fechaConsulta);
        consulta.setDuracionMinutos(15);
        consulta.setEstado("PENDIENTE");
        consulta.setMedico(medico);
        consulta.setPaciente(pacienteEntity); // ✅ método válido

        return consultaRepo.save(consulta);
    }

    @Override
    public Optional<ConsultaMedicaEntity> buscarPorId(Long idConsulta) {
        return consultaRepo.findById(idConsulta);
    }

    /**
     * Lógica para calcular la siguiente hora disponible en horario hábil.
     */
    private LocalDateTime calcularSiguienteHoraDisponible(MedicoEntity medico) {
        LocalDateTime fecha = LocalDateTime.now().withSecond(0).withNano(0);

        int minutos = fecha.getMinute();
        int sobrante = minutos % 15;
        if (sobrante != 0) {
            fecha = fecha.plusMinutes(15 - sobrante);
        }

        while (true) {
            if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fecha = fecha.plusDays(1).withHour(8).withMinute(0);
                continue;
            }

            if (fecha.getHour() < 8) {
                fecha = fecha.withHour(8).withMinute(0);
                continue;
            }

            if (fecha.getHour() >= 17) {
                fecha = fecha.plusDays(1).withHour(8).withMinute(0);
                continue;
            }

            if ((fecha.getHour() == 12) ||
                (fecha.getHour() == 10 && fecha.getMinute() < 15) ||
                (fecha.getHour() == 15 && fecha.getMinute() < 15)) {
                fecha = fecha.plusMinutes(15);
                continue;
            }

            LocalDateTime fin = fecha.plusMinutes(15);
            boolean ocupado = consultaRepo.existsByMedicoAndFechaHoraConsultaBetween(medico, fecha, fin.minusSeconds(1));
            if (!ocupado) {
                return fecha;
            }

            fecha = fecha.plusMinutes(15);
        }
    }
}

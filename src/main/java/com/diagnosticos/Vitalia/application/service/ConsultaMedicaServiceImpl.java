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
        MedicoEntity medico = validarYObtenerMedico(dto.getIdMedico());
        PacienteEntity paciente = validarYObtenerPaciente(dto.getIdPaciente());

        LocalDateTime fechaConsulta = dto.getFechaHoraConsulta();
        if (fechaConsulta == null) {
            fechaConsulta = calcularSiguienteHoraDisponible(medico.getIdMedico());
        } else {
            validarHorarioConsulta(fechaConsulta);
            validarDisponibilidad(medico.getIdMedico(), fechaConsulta);
        }

        ConsultaMedicaEntity consulta = new ConsultaMedicaEntity();
        consulta.setFechaHoraConsulta(fechaConsulta);
        consulta.setDuracionMinutos(15);
        consulta.setEstado("PENDIENTE");
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        return consultaRepo.save(consulta);
    }

    @Override
    public Optional<ConsultaMedicaEntity> buscarPorId(Long idConsulta) {
        return consultaRepo.findById(idConsulta);
    }

    // ===== MÉTODOS PRIVADOS =====

    private MedicoEntity validarYObtenerMedico(Long idMedico) {
        return medicoRepo.findById(idMedico)
                .orElseThrow(() -> new RuntimeException("❌ Médico no encontrado con ID: " + idMedico));
    }

    private PacienteEntity validarYObtenerPaciente(Long idPaciente) {
        UserEntity user = userRepo.findById(idPaciente)
                .filter(u -> "PACIENTE".equalsIgnoreCase(u.getRol()))
                .orElseThrow(() -> new RuntimeException("❌ Usuario no es un paciente válido: " + idPaciente));

        return pacienteRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("❌ Paciente no encontrado para ID de usuario: " + idPaciente));
    }

    private void validarHorarioConsulta(LocalDateTime fecha) {
        if (fecha.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("❌ No se puede agendar una consulta en el pasado.");
        }

        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new RuntimeException("❌ No se puede agendar citas los fines de semana.");
        }

        int hora = fecha.getHour();
        int minuto = fecha.getMinute();
        if (hora < 8 || hora >= 17) {
            throw new RuntimeException("❌ Horario fuera de la jornada laboral (8:00–17:00).");
        }

        boolean enDescanso =
            (hora == 10 && minuto >= 15 && minuto < 30) || // 10:15 a 10:29
            (hora == 12) ||                                // 12:00 a 12:59
            (hora == 13 && minuto == 0) ||                 // 13:00
            (hora == 15 && minuto >= 0 && minuto < 15);    // 15:00 a 15:14

        if (enDescanso) {
            throw new RuntimeException("❌ Horario en periodo de descanso o almuerzo.");
        }

        if (minuto % 15 != 0) {
            throw new RuntimeException("❌ Las citas deben comenzar en bloques de 15 minutos (ej. 8:00, 8:15, 8:30).");
        }
    }

    private void validarDisponibilidad(Long medicoId, LocalDateTime inicio) {
        LocalDateTime fin = inicio.plusMinutes(15);
        boolean traslape = consultaRepo.existeTraslape(medicoId, inicio, fin);
        if (traslape) {
            throw new RuntimeException("❌ El horario ya está ocupado por otra consulta.");
        }
    }

    private LocalDateTime calcularSiguienteHoraDisponible(Long medicoId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fecha = now.withHour(8).withMinute(0).withSecond(0).withNano(0);

        if (now.getHour() >= 17) {
            fecha = fecha.plusDays(1).withHour(8).withMinute(0);
        }

        while (true) {
            if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fecha = fecha.plusDays(1).withHour(8).withMinute(0);
                continue;
            }

            int hora = fecha.getHour();
            int minuto = fecha.getMinute();

            if (hora < 8) {
                fecha = fecha.withHour(8).withMinute(0);
                continue;
            }
            if (hora >= 17) {
                fecha = fecha.plusDays(1).withHour(8).withMinute(0);
                continue;
            }

            boolean enDescanso = 
                (hora == 10 && minuto >= 15 && minuto < 30) ||
                (hora == 12) ||
                (hora == 13 && minuto == 0) ||
                (hora == 15 && minuto >= 0 && minuto < 15);

            if (enDescanso) {
                fecha = fecha.plusMinutes(15);
                continue;
            }

            LocalDateTime fin = fecha.plusMinutes(15);
            boolean ocupado = consultaRepo.existeTraslape(medicoId, fecha, fin);
            if (!ocupado) {
                return fecha;
            }

            fecha = fecha.plusMinutes(15);
        }
    }
}

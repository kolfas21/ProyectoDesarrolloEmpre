package com.diagnosticos.Vitalia.infrastructure.adapter.controller;

import com.diagnosticos.Vitalia.application.service.PacienteService;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.PacienteDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.ActualizarPacienteDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.PacienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    // 🟢 Registro del paciente (usado tras creación de cita)
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody PacienteDTO dto) {
        pacienteService.registrarPaciente(dto);
        return ResponseEntity.ok("✅ Paciente registrado correctamente");
    }

    // 🔎 Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<PacienteEntity>> obtenerTodos() {
        return ResponseEntity.ok(pacienteService.obtenerTodos());
    }

    // 🔎 Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteEntity> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtenerPorId(id));
    }

    // ✏️ Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody ActualizarPacienteDTO dto) {
        pacienteService.actualizarPaciente(id, dto);
        return ResponseEntity.ok("✅ Paciente actualizado correctamente");
    }

    // ❌ Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("✅ Paciente eliminado correctamente");
    }

    // 🩺 Completar datos clínicos desde cita (usado por el médico)
    @PutMapping("/completar")
    public ResponseEntity<String> completarDatosMedicos(@RequestBody PacienteDTO dto) {
        pacienteService.completarDatosMedicos(dto);
        return ResponseEntity.ok("✅ Datos médicos del paciente completados");
    }

    // ✅ NUEVO: Médico llena los datos clínicos desde una cita
    @PutMapping("/medico/completar-datos")
    public ResponseEntity<String> completarDesdeMedico(@RequestBody PacienteDTO dto) {
        try {
            pacienteService.completarDatosPaciente(dto);
            return ResponseEntity.ok("✅ Información clínica del paciente completada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ " + e.getMessage());
        }
    }
}

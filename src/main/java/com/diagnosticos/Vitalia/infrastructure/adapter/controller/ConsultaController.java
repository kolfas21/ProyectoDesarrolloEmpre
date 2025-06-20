package com.diagnosticos.Vitalia.infrastructure.adapter.controller;

import com.diagnosticos.Vitalia.application.service.ConsultaMedicaService;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.ConsultaRequestDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity.ConsultaMedicaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/usuarios/consulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaMedicaService consultaService;

    @PostMapping
    public ResponseEntity<?> crearConsulta(@RequestBody ConsultaRequestDTO dto) {
        try {
            ConsultaMedicaEntity consulta = consultaService.crearConsulta(dto);
            return ResponseEntity.ok("Consulta creada correctamente con ID: " + consulta.getIdConsulta());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.diagnosticos.Vitalia.infrastructure.adapter.controller;

import com.diagnosticos.Vitalia.application.service.GenerarDiagnosticoService;
import com.diagnosticos.Vitalia.domain.model.Usuario;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.DiagnosticoRespuestaDTO;
import com.diagnosticos.Vitalia.infrastructure.adapter.controller.dto.DiagnosticoSolicitudDTO;
import com.diagnosticos.Vitalia.infrastructure.strategy.DiagnosticoContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller responsable de manejar la petición HTTP del diagnóstico.
 * Está en la capa de infraestructura (entrada/salida del sistema).
 * Patrón "Controller" y "DTO mapping".
 */


import java.util.*;

@RestController
@RequestMapping("/diagnostico")
@RequiredArgsConstructor
public class DiagnosticoController {

    private final DiagnosticoContext diagnosticoContext;

    // Implementación en memoria
    private final Map<String, Usuario> usuarioDB = new HashMap<>();

    // POST para crear un usuario
    @PostMapping("/usuario")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        usuarioDB.put(usuario.getNombre(), usuario);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    // GET para traer todos los usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>(usuarioDB.values());
        return ResponseEntity.ok(usuarios);
    }

    private final GenerarDiagnosticoService service;

    @PostMapping("/generar")
    public DiagnosticoRespuestaDTO generarDiagnostico(@RequestBody DiagnosticoSolicitudDTO dto) {
        return service.generarDiagnostico(dto);
    }

}
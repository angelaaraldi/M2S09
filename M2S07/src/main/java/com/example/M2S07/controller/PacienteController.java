package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.PacienteRequest;
import com.example.M2S07.controller.dto.PacienteResponse;
import com.example.M2S07.service.PacienteService;
import com.example.M2S07.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;
    private final TokenService tokenService;
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<PacienteResponse>> getPacientes() {
        List<PacienteResponse> response = pacienteService.buscarPacientes();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<PacienteResponse> postPaciente(@RequestBody PacienteRequest request) {
        PacienteResponse response = pacienteService.salvarPaciente(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> putPaciente(@PathVariable Long id, @RequestBody PacienteRequest request, @RequestHeader(name = "Authorization") String token) {
        if (tokenService.validaToken(token, "ADMIN") || tokenService.validaToken(token, "PACIENTE") && pacienteService.validarId(token, id)) {
            PacienteResponse response = pacienteService.atualizarPaciente(id, request);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponse> deletePaciente(@PathVariable Long id) {
        PacienteResponse response = pacienteService.deletarPaciente(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPaciente(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        if (tokenService.validaToken(token, "ADMIN") || tokenService.validaToken(token, "PACIENTE") && pacienteService.validarId(token, id)) {
            PacienteResponse response = pacienteService.buscarPaciente(id);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

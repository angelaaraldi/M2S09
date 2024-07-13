package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.ConsultaRequest;
import com.example.M2S07.controller.dto.ConsultaResponse;
import com.example.M2S07.service.ConsultaService;
import com.example.M2S07.service.PacienteService;
import com.example.M2S07.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsultaController {
    private final ConsultaService consultaService;
    private final TokenService tokenService;
    private final PacienteService pacienteService;
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_NUTRICIONISTA')")
    @GetMapping("/consultas")
    public ResponseEntity<List<ConsultaResponse>> getConsultas() {
        List<ConsultaResponse> response = consultaService.buscarConsultas();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_NUTRICIONISTA')")
    @PostMapping("/consultas")
    public ResponseEntity<ConsultaResponse> postConsulta(@RequestBody ConsultaRequest request) {
        ConsultaResponse response = consultaService.salvarConsulta(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_NUTRICIONISTA')")
    @PutMapping("/consultas/{id}")
    public ResponseEntity<ConsultaResponse> putConsulta(@PathVariable Long id, @RequestBody ConsultaRequest request) {
        ConsultaResponse response = consultaService.atualizarConsulta(id, request);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_NUTRICIONISTA')")
    @DeleteMapping("/consultas/{id}")
    public ResponseEntity<ConsultaResponse> deleteConsulta(@PathVariable Long id) {
        ConsultaResponse response = consultaService.deletarConsulta(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/minhas-consultas")
    public ResponseEntity<List<ConsultaResponse>> getMinhasConsultas(@RequestBody Long id, @RequestHeader(name = "Authorization") String token) {
        if (tokenService.validaToken(token, "ADMIN") || tokenService.validaToken(token, "PACIENTE") && pacienteService.validarId(token, id)) {
            List<ConsultaResponse> response = consultaService.buscarMinhasConsultas(id);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

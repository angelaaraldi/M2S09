package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.FuncionarioRequest;
import com.example.M2S07.controller.dto.FuncionarioResponse;
import com.example.M2S07.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> getFuncionarios() {
        List<FuncionarioResponse> response = funcionarioService.buscarFuncionarios();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<FuncionarioResponse> postFuncionario(@RequestBody FuncionarioRequest request) {
        FuncionarioResponse response = funcionarioService.salvarFuncionario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> putFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequest request) {
        FuncionarioResponse response = funcionarioService.atualizarFuncionario(id, request);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> deleteFuncionario(@PathVariable Long id) {
        FuncionarioResponse response = funcionarioService.deletarFuncionario(id);
        return ResponseEntity.ok(response);
    }
}

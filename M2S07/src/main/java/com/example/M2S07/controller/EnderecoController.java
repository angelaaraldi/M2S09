package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.EnderecoRequest;
import com.example.M2S07.controller.dto.EnderecoResponse;
import com.example.M2S07.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> getEnderecos() {
        List<EnderecoResponse> response = enderecoService.buscarEnderecos();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<EnderecoResponse> postEndereco(@RequestBody EnderecoRequest request) {
        EnderecoResponse response = enderecoService.salvarEndereco(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponse> putEndereco(@PathVariable Long id, @RequestBody EnderecoRequest request) {
        EnderecoResponse response = enderecoService.atualizarEndereco(id, request);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoResponse> deleteEndereco(@PathVariable Long id) {
        EnderecoResponse response = enderecoService.deletarEndereco(id);
        return ResponseEntity.ok(response);
    }
}

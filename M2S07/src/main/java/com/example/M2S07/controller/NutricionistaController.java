package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.NutricionistaRequest;
import com.example.M2S07.controller.dto.NutricionistaResponse;
import com.example.M2S07.service.NutricionistaService;
import com.example.M2S07.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutricionistas")
@RequiredArgsConstructor
public class NutricionistaController {
    private final NutricionistaService nutricionistaService;
    private final TokenService tokenService;
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<NutricionistaResponse>> getNutricionistas() {
        List<NutricionistaResponse> response = nutricionistaService.buscarNutricionistas();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<NutricionistaResponse> postNutricionista(@RequestBody NutricionistaRequest request) {
        NutricionistaResponse response = nutricionistaService.salvarNutricionista(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaResponse> putNutricionista(@PathVariable Long id, @RequestBody NutricionistaRequest request, @RequestHeader(name = "Authorization") String token) {
        if (tokenService.validaToken(token, "ADMIN") || tokenService.validaToken(token, "NUTRICIONISTA") && nutricionistaService.validarId(token, id)) {
            NutricionistaResponse response = nutricionistaService.atualizarNutricionista(id, request);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<NutricionistaResponse> deleteNutricionista(@PathVariable Long id) {
        NutricionistaResponse response = nutricionistaService.deletarNutricionista(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaResponse> getNutricionista(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        if (tokenService.validaToken(token, "ADMIN") || tokenService.validaToken(token, "NUTRICIONISTA") && nutricionistaService.validarId(token, id)) {
            NutricionistaResponse response = nutricionistaService.buscarNutricionista(id);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

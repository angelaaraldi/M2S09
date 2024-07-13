package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.PerfilRequest;
import com.example.M2S07.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfilController {
    private final PerfilService perfilService;
    @PostMapping("/perfil")
    public ResponseEntity<String> cadastraPerfil(@RequestBody PerfilRequest perfilRequest) {
        perfilService.cadastraPerfil(perfilRequest);
        return new ResponseEntity<>("Perfil Criado", HttpStatus.CREATED);
    }
    @GetMapping("/perfis")
    public ResponseEntity<String> carregarPerfis() {
        perfilService.carregarPerfis();
        return new ResponseEntity<>("Perfis carregados", HttpStatus.OK);
    }
}

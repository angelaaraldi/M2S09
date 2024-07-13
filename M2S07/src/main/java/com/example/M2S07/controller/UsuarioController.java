package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.LoginRequest;
import com.example.M2S07.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastraUsuario(@RequestBody LoginRequest cadastroRequest) {
        usuarioService.cadastraUsuario(cadastroRequest);
        return new ResponseEntity<>("Usuário Criado", HttpStatus.CREATED);
    }
}

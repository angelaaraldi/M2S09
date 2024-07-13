package com.example.M2S07.service;

import com.example.M2S07.controller.dto.LoginRequest;
import com.example.M2S07.entity.PerfilEntity;
import com.example.M2S07.entity.UsuarioEntity;
import com.example.M2S07.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PerfilService perfilService;
    public UsuarioEntity validaUsuario(LoginRequest loginRequest) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByUsername(loginRequest.username()).orElseThrow(() -> new RuntimeException("Usuário não existe com o nome " + loginRequest.username()));
        if (!passwordEncoder.matches(loginRequest.password(), usuarioEntity.getPassword())) {
            throw new RuntimeException("Senha errada para usuário com nome " + loginRequest.username());
        }
        return usuarioEntity;
    }

    public void cadastraUsuario(LoginRequest cadastroRequest) {
        if (usuarioRepository.findByUsername(cadastroRequest.username()).isPresent()) {
            throw new RuntimeException("Usuário existe com nome: " + cadastroRequest.username());
        }
        PerfilEntity perfilEntity = perfilService.validaPerfil(cadastroRequest.nomePerfil());
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(cadastroRequest.username());
        usuarioEntity.setPassword(passwordEncoder.encode(cadastroRequest.password()));
        usuarioEntity.setPerfilEntityList(Set.of(perfilEntity));
        usuarioRepository.save(usuarioEntity);
    }
}

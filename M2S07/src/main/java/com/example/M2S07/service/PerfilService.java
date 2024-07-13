package com.example.M2S07.service;

import com.example.M2S07.controller.dto.PerfilRequest;
import com.example.M2S07.entity.PerfilEntity;
import com.example.M2S07.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PerfilService {
    private final PerfilRepository repository;

    public void cadastraPerfil(PerfilRequest perfilRequest) {
        if (repository.findByNomePerfil(perfilRequest.nomePerfil()).isPresent()) {
            throw new RuntimeException("Perfil já existe com o nome: " + perfilRequest.nomePerfil());
        }
        PerfilEntity perfilEntity = new PerfilEntity();
        perfilEntity.setNomePerfil(perfilRequest.nomePerfil());
        repository.save(perfilEntity);
    }
    public PerfilEntity validaPerfil(String nomePerfil) {
       PerfilEntity perfil = repository.findByNomePerfil(nomePerfil).orElseThrow(() -> new RuntimeException("Perfil não existe com nome: " + nomePerfil));
        return perfil;
    }
    public void carregarPerfis() {
        if (repository.findByNomePerfil("ADMIN").isEmpty()) {
            repository.save(new PerfilEntity("ADMIN"));
        }
        if (repository.findByNomePerfil("NUTRICIONISTA").isEmpty()) {
            repository.save(new PerfilEntity("NUTRICIONISTA"));
        }
        if (repository.findByNomePerfil("PACIENTE").isEmpty()) {
            repository.save(new PerfilEntity("PACIENTE"));
        }
    }
}

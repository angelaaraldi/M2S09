package com.example.M2S07.service;

import com.example.M2S07.controller.dto.FuncionarioRequest;
import com.example.M2S07.controller.dto.FuncionarioResponse;
import com.example.M2S07.entity.FuncionarioEntity;
import com.example.M2S07.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository repository;
    public List<FuncionarioResponse> buscarFuncionarios() {
        return repository.findAll().stream().map(funcionarioEntity -> new FuncionarioResponse(funcionarioEntity.getId(), funcionarioEntity.getNome(), funcionarioEntity.getIdade(), funcionarioEntity.getEndereco(), funcionarioEntity.getSalario())).collect(Collectors.toList());
    }
    public FuncionarioResponse salvarFuncionario(FuncionarioRequest request) {
        FuncionarioEntity entity = new FuncionarioEntity();
        entity.setNome(request.getNome());
        entity.setIdade(request.getIdade());
        entity.setEndereco(request.getEndereco());
        entity.setSalario(request.getSalario());
        FuncionarioEntity entitySalva = repository.save(entity);
        return new FuncionarioResponse(entitySalva.getId(), entitySalva.getNome(), entitySalva.getIdade(), entitySalva.getEndereco(), entitySalva.getSalario());
    }
    public FuncionarioResponse atualizarFuncionario(Long id, FuncionarioRequest request) {
        FuncionarioEntity funcionario = repository.findById(id).orElse(null);
        if (funcionario != null) {
            funcionario.setNome(request.getNome());
            funcionario.setIdade(request.getIdade());
            funcionario.setEndereco(request.getEndereco());
            funcionario.setSalario(request.getSalario());
            FuncionarioEntity funcionarioAtualizado = repository.save(funcionario);
            return new FuncionarioResponse(funcionarioAtualizado.getId(), funcionarioAtualizado.getNome(), funcionarioAtualizado.getIdade(), funcionarioAtualizado.getEndereco(), funcionarioAtualizado.getSalario());
        }
        return null;
    }
    public FuncionarioResponse deletarFuncionario(Long id) {
        FuncionarioEntity funcionario = repository.findById(id).orElse(null);
        if (funcionario != null) {
            repository.delete(funcionario);
            return new FuncionarioResponse(funcionario.getId(), funcionario.getNome(), funcionario.getIdade(), funcionario.getEndereco(), funcionario.getSalario());
        }
        return null;
    }
}

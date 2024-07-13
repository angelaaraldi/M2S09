package com.example.M2S07.service;

import com.example.M2S07.controller.dto.EnderecoRequest;
import com.example.M2S07.controller.dto.EnderecoResponse;
import com.example.M2S07.entity.EnderecoEntity;
import com.example.M2S07.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository repository;
    public List<EnderecoResponse> buscarEnderecos() {
        return repository.findAll().stream().map(enderecoEntity -> new EnderecoResponse(enderecoEntity.getId(), enderecoEntity.getLogradouro(), enderecoEntity.getEstado(), enderecoEntity.getCidade(), enderecoEntity.getNumero(), enderecoEntity.getCep())).collect(Collectors.toList());
    }
    public EnderecoResponse salvarEndereco(EnderecoRequest request) {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setLogradouro(request.getLogradouro());
        entity.setEstado(request.getEstado());
        entity.setCidade(request.getCidade());
        entity.setNumero(request.getNumero());
        entity.setCep(request.getCep());
        EnderecoEntity entitySalva = repository.save(entity);
        return new EnderecoResponse(entitySalva.getId(), entitySalva.getLogradouro(), entitySalva.getEstado(), entitySalva.getCidade(), entitySalva.getNumero(), entitySalva.getCep());
    }
    public EnderecoResponse atualizarEndereco(Long id, EnderecoRequest request) {
        EnderecoEntity endereco = repository.findById(id).orElse(null);
        if (endereco != null) {
            endereco.setLogradouro(request.getLogradouro());
            endereco.setEstado(request.getEstado());
            endereco.setCidade(request.getCidade());
            endereco.setNumero(request.getNumero());
            endereco.setCep(request.getCep());
            EnderecoEntity enderecoAtualizado = repository.save(endereco);
            return new EnderecoResponse(enderecoAtualizado.getId(), enderecoAtualizado.getLogradouro(), enderecoAtualizado.getEstado(), enderecoAtualizado.getCidade(), enderecoAtualizado.getNumero(), enderecoAtualizado.getCep());
        }
        return null;
    }
    public EnderecoResponse deletarEndereco(Long id) {
        EnderecoEntity endereco = repository.findById(id).orElse(null);
        if (endereco != null) {
            repository.delete(endereco);
            return new EnderecoResponse(endereco.getId(), endereco.getLogradouro(), endereco.getEstado(), endereco.getCidade(), endereco.getNumero(), endereco.getCep());
        }
        return null;
    }
}

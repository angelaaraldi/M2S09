package com.example.M2S07.service;

import com.example.M2S07.controller.dto.PacienteRequest;
import com.example.M2S07.controller.dto.PacienteResponse;
import com.example.M2S07.entity.PacienteEntity;
import com.example.M2S07.entity.UsuarioEntity;
import com.example.M2S07.repository.PacienteRepository;
import com.example.M2S07.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository repository;
    private final JwtDecoder jwtDecoder;
    private final UsuarioRepository usuarioRepository;
    public List<PacienteResponse> buscarPacientes() {
        return repository.findAll().stream().map(pacienteEntity -> new PacienteResponse(pacienteEntity.getId(), pacienteEntity.getNome(), pacienteEntity.getDataNascimento(), pacienteEntity.getCpf(), pacienteEntity.getTelefone(), pacienteEntity.getEmail(), pacienteEntity.getEndereco(), pacienteEntity.getIdUsuario())).collect(Collectors.toList());
    }
    public PacienteResponse salvarPaciente(PacienteRequest request) {
        PacienteEntity entity = new PacienteEntity();
        entity.setNome(request.getNome());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setCpf(request.getCpf());
        entity.setTelefone(request.getTelefone());
        entity.setEmail(request.getEmail());
        entity.setEndereco(request.getEndereco());
        entity.setIdUsuario(request.getIdUsuario());
        PacienteEntity entitySalva = repository.save(entity);
        return new PacienteResponse(entitySalva.getId(), entitySalva.getNome(), entitySalva.getDataNascimento(), entitySalva.getCpf(), entitySalva.getTelefone(), entitySalva.getEmail(), entitySalva.getEndereco(), entitySalva.getIdUsuario());
    }
    public PacienteResponse atualizarPaciente(Long id, PacienteRequest request) {
        PacienteEntity paciente = repository.findById(id).orElse(null);
        if (paciente != null) {
            paciente.setNome(request.getNome());
            paciente.setDataNascimento(request.getDataNascimento());
            paciente.setCpf(request.getCpf());
            paciente.setTelefone(request.getTelefone());
            paciente.setEmail(request.getEmail());
            paciente.setEndereco(request.getEndereco());
            paciente.setIdUsuario(request.getIdUsuario());
            PacienteEntity pacienteAtualizado = repository.save(paciente);
            return new PacienteResponse(pacienteAtualizado.getId(), pacienteAtualizado.getNome(), pacienteAtualizado.getDataNascimento(), pacienteAtualizado.getCpf(), pacienteAtualizado.getTelefone(), pacienteAtualizado.getEmail(), pacienteAtualizado.getEndereco(), pacienteAtualizado.getIdUsuario());
        }
        return null;
    }
    public PacienteResponse deletarPaciente(Long id) {
        PacienteEntity paciente = repository.findById(id).orElse(null);
        if (paciente != null) {
            repository.delete(paciente);
            return new PacienteResponse(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail(), paciente.getEndereco(), paciente.getIdUsuario());
        }
        return null;
    }
    public PacienteResponse buscarPaciente(Long id) {
        PacienteEntity paciente = repository.findById(id).orElse(null);
        if (paciente != null) {
            return new PacienteResponse(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail(), paciente.getEndereco(), paciente.getIdUsuario());
        }
        return null;
    }
    public boolean validarId(String token, Long id) {
        String tokenReal = token.split(" ")[1];
        String sub = jwtDecoder.decode(tokenReal).getClaim("sub").toString();
        UsuarioEntity usuario = usuarioRepository.findByUsername(sub).orElse(null);
        PacienteEntity paciente = repository.findById(id).orElse(null);
        return usuario != null && paciente != null && Objects.equals(usuario.getId(), paciente.getIdUsuario());
    }
}

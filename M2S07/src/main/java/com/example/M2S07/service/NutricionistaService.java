package com.example.M2S07.service;

import com.example.M2S07.controller.dto.NutricionistaRequest;
import com.example.M2S07.controller.dto.NutricionistaResponse;
import com.example.M2S07.entity.NutricionistaEntity;
import com.example.M2S07.entity.UsuarioEntity;
import com.example.M2S07.repository.NutricionistaRepository;
import com.example.M2S07.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NutricionistaService {
    private final NutricionistaRepository repository;
    private final JwtDecoder jwtDecoder;
    private final UsuarioRepository usuarioRepository;
    public List<NutricionistaResponse> buscarNutricionistas() {
        return repository.findAll().stream().map(nutricionistaEntity -> new NutricionistaResponse(nutricionistaEntity.getId(), nutricionistaEntity.getCrn(), nutricionistaEntity.getEspecialidade(), nutricionistaEntity.getTelefone(), nutricionistaEntity.getNome(), nutricionistaEntity.getTempoExperiencia(), nutricionistaEntity.getCertificacoes(), nutricionistaEntity.getIdUsuario())).collect(Collectors.toList());
    }
    public List<NutricionistaResponse> buscarNome(String nome) {
        return repository.findAllByNome(nome).stream().map(nutricionistaEntity -> new NutricionistaResponse(nutricionistaEntity.getId(), nutricionistaEntity.getCrn(), nutricionistaEntity.getEspecialidade(), nutricionistaEntity.getTelefone(), nutricionistaEntity.getNome(), nutricionistaEntity.getTempoExperiencia(), nutricionistaEntity.getCertificacoes(), nutricionistaEntity.getIdUsuario())).collect(Collectors.toList());
    }
    public NutricionistaResponse salvarNutricionista(NutricionistaRequest request) {
        NutricionistaEntity entity = new NutricionistaEntity();
        entity.setCrn(request.getCrn());
        entity.setEspecialidade(request.getEspecialidade());
        entity.setTelefone(request.getTelefone());
        if (buscarNome(request.getNome()).isEmpty()) {
            entity.setNome(request.getNome());
        }
        entity.setTempoExperiencia(request.getTempoExperiencia());
        entity.setCertificacoes(request.getCertificacoes());
        entity.setIdUsuario(request.getIdUsuario());
        NutricionistaEntity entitySalva = repository.save(entity);
        return new NutricionistaResponse(entitySalva.getId(), entitySalva.getCrn(), entitySalva.getEspecialidade(), entitySalva.getTelefone(), entitySalva.getNome(), entitySalva.getTempoExperiencia(), entitySalva.getCertificacoes(), entitySalva.getIdUsuario());
    }
    public NutricionistaResponse atualizarNutricionista(Long id, NutricionistaRequest request) {
        NutricionistaEntity nutricionista = repository.findById(id).orElse(null);
        if (nutricionista != null) {
            nutricionista.setCrn(request.getCrn());
            nutricionista.setEspecialidade(request.getEspecialidade());
            nutricionista.setTelefone(request.getTelefone());
            if (buscarNome(request.getNome()).isEmpty()) {
                nutricionista.setNome(request.getNome());
            }
            nutricionista.setTempoExperiencia(request.getTempoExperiencia());
            nutricionista.setCertificacoes(request.getCertificacoes());
            nutricionista.setIdUsuario(request.getIdUsuario());
            NutricionistaEntity nutricionistaAtualizado = repository.save(nutricionista);
            return new NutricionistaResponse(nutricionistaAtualizado.getId(), nutricionistaAtualizado.getCrn(), nutricionistaAtualizado.getEspecialidade(), nutricionistaAtualizado.getTelefone(), nutricionistaAtualizado.getNome(), nutricionistaAtualizado.getTempoExperiencia(), nutricionistaAtualizado.getCertificacoes(), nutricionistaAtualizado.getIdUsuario());
        }
        return null;
    }
    public NutricionistaResponse deletarNutricionista(Long id) {
        NutricionistaEntity nutricionista = repository.findById(id).orElse(null);
        if (nutricionista != null) {
            repository.delete(nutricionista);
            return new NutricionistaResponse(nutricionista.getId(), nutricionista.getCrn(), nutricionista.getEspecialidade(), nutricionista.getTelefone(), nutricionista.getNome(), nutricionista.getTempoExperiencia(), nutricionista.getCertificacoes(), nutricionista.getIdUsuario());
        }
        return null;
    }
    public void adicionarTempoExperiencia(Long id) {
        repository.findById(id).ifPresent(nutricionista -> nutricionista.setTempoExperiencia(nutricionista.getTempoExperiencia() + 1));
    }
    public void adicionarCertificacao(Long id, String certificacao) {
        repository.findById(id).ifPresent(nutricionista -> nutricionista.setCertificacoes(nutricionista.getCertificacoes() + ", " + certificacao));
    }
    public NutricionistaResponse buscarNutricionista(Long id) {
       NutricionistaEntity nutricionista = repository.findById(id).orElse(null);
       if (nutricionista != null) {
          return new NutricionistaResponse(nutricionista.getId(), nutricionista.getCrn(), nutricionista.getEspecialidade(), nutricionista.getTelefone(), nutricionista.getNome(), nutricionista.getTempoExperiencia(), nutricionista.getCertificacoes(), nutricionista.getIdUsuario());
       }
       return null;
    }
    public boolean validarId(String token, Long id) {
        String tokenReal = token.split(" ")[1];
        String sub = jwtDecoder.decode(tokenReal).getClaim("sub").toString();
        UsuarioEntity usuario = usuarioRepository.findByUsername(sub).orElse(null);
        NutricionistaEntity nutricionista = repository.findById(id).orElse(null);
        return usuario != null && nutricionista != null && Objects.equals(usuario.getId(), nutricionista.getIdUsuario());
    }
}

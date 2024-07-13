package com.example.M2S07.repository;

import com.example.M2S07.entity.PerfilEntity;
import com.example.M2S07.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
    Optional<PerfilEntity> findByNomePerfil(String nomePerfil);
}

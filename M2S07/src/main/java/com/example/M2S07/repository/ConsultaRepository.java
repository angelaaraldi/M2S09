package com.example.M2S07.repository;

import com.example.M2S07.entity.ConsultaEntity;
import com.example.M2S07.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {
}

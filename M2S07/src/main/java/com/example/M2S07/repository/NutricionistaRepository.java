package com.example.M2S07.repository;

import com.example.M2S07.entity.NutricionistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutricionistaRepository extends JpaRepository<NutricionistaEntity, Long> {
    List<NutricionistaEntity> findAllByNome(String nome);
}

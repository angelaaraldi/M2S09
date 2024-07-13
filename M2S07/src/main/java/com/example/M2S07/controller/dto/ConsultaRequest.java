package com.example.M2S07.controller.dto;

import com.example.M2S07.entity.NutricionistaEntity;
import com.example.M2S07.entity.PacienteEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaRequest {
    private NutricionistaEntity nutricionista;
    private PacienteEntity paciente;
    private LocalDate dataConsulta;
    private String observacoes;
    private LocalTime horaConsulta;
}

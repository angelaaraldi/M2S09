package com.example.M2S07.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NutricionistaResponse {
    private Long id;
    private String crn;
    private String especialidade;
    private String telefone;
    private String nome;
    private int tempoExperiencia;
    private String certificacoes;
    private Long idUsuario;
}

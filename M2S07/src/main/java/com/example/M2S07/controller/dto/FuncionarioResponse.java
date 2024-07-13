package com.example.M2S07.controller.dto;

import com.example.M2S07.entity.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FuncionarioResponse {
    private Long id;
    private String nome;
    private int idade;
    private EnderecoEntity endereco;
    private double salario;
}

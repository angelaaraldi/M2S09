package com.example.M2S07.controller.dto;

import com.example.M2S07.entity.EnderecoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FuncionarioRequest {
    private String nome;
    private int idade;
    private EnderecoEntity endereco;
    private double salario;
}

package com.example.M2S07.controller.dto;

import com.example.M2S07.entity.EnderecoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class PacienteRequest {
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private EnderecoEntity endereco;
    private Long idUsuario;
}

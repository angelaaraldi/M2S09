package com.example.M2S07.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoResponse {
    private Long id;
    private String logradouro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep;
}

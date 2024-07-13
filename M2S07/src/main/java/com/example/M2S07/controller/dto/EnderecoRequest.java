package com.example.M2S07.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnderecoRequest {
    private String logradouro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep;
}

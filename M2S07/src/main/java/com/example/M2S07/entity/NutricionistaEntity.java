package com.example.M2S07.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nutricionista")
@Data
public class NutricionistaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "crn")
    private String crn;
    @Column(name = "especialidade")
    private String especialidade;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "nome")
    private String nome;
    @Column(name = "tempo_experiencia")
    private int tempoExperiencia;
    @Column(name = "certificacoes")
    private String certificacoes;
    @Column(name = "id_usuario")
    private Long idUsuario;
}

package com.example.M2S07.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "funcionario")
@Data
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "idade")
    private int idade;
    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity endereco;
    @Column(name = "salario")
    private double salario;
}

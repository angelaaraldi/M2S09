package com.example.M2S07.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "consulta")
@Data
public class ConsultaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_nutricionista")
    private NutricionistaEntity nutricionista;
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;
    @Column(name = "data_consulta")
    private LocalDate dataConsulta;
    @Column(name = "observacoes")
    private String observacoes;
    @Column(name = "hora_consulta")
    private LocalTime horaConsulta;
}

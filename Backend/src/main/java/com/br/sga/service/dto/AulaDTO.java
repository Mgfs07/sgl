package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaDTO implements Serializable {

    private Long id;
    private String nome;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String turma;
    private Long idPeriodo;
    private Long idProfessor;
    private Long idDiaSemana;
    private Long idDisciplina;
    private Long idLocal;

}

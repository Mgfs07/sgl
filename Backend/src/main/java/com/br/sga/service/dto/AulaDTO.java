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

    private Integer id;
    private String nome;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String turma;
    private Integer idPeriodo;
    private Integer idProfessor;
    private Integer idDiaSemana;
    private Integer idDisciplina;
    private Integer idLocal;

}

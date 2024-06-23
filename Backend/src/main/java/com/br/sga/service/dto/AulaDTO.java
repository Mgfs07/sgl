package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class AulaDTO implements Serializable {


    private Long id;
    private String nome;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Long idLocal;
    private String professor;
    private String diaSemana;
    private Long idDiaSemana;
    private String disciplina;
}

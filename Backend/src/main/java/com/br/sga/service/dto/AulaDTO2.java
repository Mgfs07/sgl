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
public class AulaDTO2 implements Serializable {

    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String turma;
    private String periodo;
    private String idDiaSemana;
    private Long idLocal;
    private String nomeLocal;
}

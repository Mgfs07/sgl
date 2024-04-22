package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class HorariosAula implements Serializable {

    private Long id;
    private String nomeAula;
    private String professor;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String diaSemana;
    private String local;
}

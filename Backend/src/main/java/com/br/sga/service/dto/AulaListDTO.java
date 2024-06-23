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
public class AulaListDTO implements Serializable {

    private Long id;
    private String nome;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String professor;
    private String local;

}

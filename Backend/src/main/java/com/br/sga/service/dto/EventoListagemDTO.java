package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoListagemDTO implements Serializable {

    private Long id;
    private String descricao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private LocalDate data;
    private String nomeLocal;
}

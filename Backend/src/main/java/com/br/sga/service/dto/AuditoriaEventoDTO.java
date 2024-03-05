package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaEventoDTO implements Serializable {

    private Integer id;
    private String acao;
    private LocalDate data;
    private Integer idCoordenador;
    private Integer idProfessor;
    private Integer idEvento;
}

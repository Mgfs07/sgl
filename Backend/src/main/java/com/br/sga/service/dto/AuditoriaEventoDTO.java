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

    private Long id;
    private String acao;
    private LocalDate data;
    private String matriculaSolicitante;
    private String matriculaUsuarioLogado;
    private String nomeUsuarioAlteracao;
    private Long idEvento;
}

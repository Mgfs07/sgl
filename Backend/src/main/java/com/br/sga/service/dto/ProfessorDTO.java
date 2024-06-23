package com.br.sga.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProfessorDTO implements Serializable {

    private Long matricula;
    private String nome;
    private Long idCoordenadoria;
}

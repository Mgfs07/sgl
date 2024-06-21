package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class FIltroDTO implements Serializable {

    private String matriculaProfessor;
    private Long idPeriodo;
    private Long idTurma;
    private String matriculaAluno;
    private Long idLaboratorio;
}

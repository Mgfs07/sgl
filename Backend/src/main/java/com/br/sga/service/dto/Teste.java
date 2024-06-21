package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Teste implements Serializable {

    private String ator;
    private String curso;
    private String periodo;
    List<HorariosAula2> aulas = new ArrayList<>();

    public Teste(String ator, String curso, String periodo) {
        this.ator = ator;
        this.curso = curso;
        this.periodo = periodo;
    }
}

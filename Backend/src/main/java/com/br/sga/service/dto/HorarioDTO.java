package com.br.sga.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HorarioDTO implements Serializable {

    private String ator;
    private String periodo;
    private Long idTurma;
    List<HorariosAula2> aulas = new ArrayList<>();

    public HorarioDTO(String ator, String periodo) {
        this.ator = ator;
        this.periodo = periodo;
    }

    public HorarioDTO(String ator, String periodo, Long idTurma) {
        this.ator = ator;
        this.periodo = periodo;
        this.idTurma = idTurma;
    }
}

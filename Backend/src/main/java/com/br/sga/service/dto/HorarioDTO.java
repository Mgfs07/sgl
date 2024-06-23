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
    List<HorariosAula2> aulas = new ArrayList<>();

    public HorarioDTO(String ator, String curso, String periodo) {
        this.ator = ator;
        this.periodo = periodo;
    }
}

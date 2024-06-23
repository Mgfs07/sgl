package com.br.sga.service.dto;

import com.br.sga.domain.enums.TipoAtorBuscaEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HorarioFiltroDTO implements Serializable {

    private TipoAtorBuscaEnum tipoAtorBusca;
    private String matricula;
    private Long idTurma;

}

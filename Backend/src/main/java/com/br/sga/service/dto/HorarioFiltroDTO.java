package com.br.sga.service.dto;

import com.br.sga.domain.enums.TipoAtorBuscaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class HorarioFiltroDTO implements Serializable {

    private TipoAtorBuscaEnum tipoAtorBusca;
    private String rfId;
    private String matricula;
    private Long idTurma;

}

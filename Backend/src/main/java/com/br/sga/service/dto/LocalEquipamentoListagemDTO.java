package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocalEquipamentoListagemDTO implements Serializable {

    private Long idLocal;
    private Long idEquipamento;
    private Integer quantidade;
    private String nomeEquipamento;
}

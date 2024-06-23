package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AlocacaoLocalAulaDTO implements Serializable {
    private Long idLocal;
    private Long idAula;
}

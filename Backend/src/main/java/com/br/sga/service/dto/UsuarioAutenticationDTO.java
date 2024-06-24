package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioAutenticationDTO implements Serializable {

    private String matricula;
    private String nome;
    private String senha;
}

package com.br.sga.config.auth;

import com.br.sga.domain.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO implements Serializable {

    private String matricula;
    private String nome;
    private List<Roles> roles;
}

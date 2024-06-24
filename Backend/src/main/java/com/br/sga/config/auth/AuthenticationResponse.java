package com.br.sga.config.auth;

import com.br.sga.domain.Roles;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private String matricula;
    private String nome;
    private List<Roles> roles;
    private String token;
    private long expiresIn;
}

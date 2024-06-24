package com.br.sga.config.auth;

import com.br.sga.config.JwtService;
import com.br.sga.domain.Usuario;
import com.br.sga.service.dto.UsuarioAutenticationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody UsuarioAutenticationDTO usuario){
        return ResponseEntity.ok(authenticationService.register(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        Usuario usuario  = authenticationService.authenticate(authenticationRequest);
        String jwtToken = jwtService.generateToken(usuario);
        AuthenticationResponse loginReponse = AuthenticationResponse.builder()
                .matricula(usuario.getMatricula())
                .nome(usuario.getNome())
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .roles(usuario.getRoles()).build();
        return ResponseEntity.ok(loginReponse);
    }
}

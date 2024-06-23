package com.br.sga.config.auth;

import com.br.sga.config.JwtService;
import com.br.sga.domain.Roles;
import com.br.sga.domain.Usuario;
import com.br.sga.repository.UsuarioRepository;
import com.br.sga.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Usuario register(UsuarioDTO usuario) {
        Usuario user = new Usuario(
                usuario.getMatricula(),
                usuario.getNome(),
                passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(user);
    }

    public Usuario authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        return usuarioRepository.findByIdWithRoles(authenticationRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
    }
}

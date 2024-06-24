package com.br.sga.service;

import com.br.sga.domain.Usuario;
import com.br.sga.repository.UsuarioRepository;
import com.br.sga.service.dto.UsuarioDTO;
import com.br.sga.service.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public Usuario validarMatriculaUsuario(String matricula) {
        return usuarioRepository.findById(matricula).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matricula não cadastrada como usuario"));
    }

    public UsuarioDTO buscarUsuarioPorMatricula(String matricula) {
        return usuarioMapper.toDto(validarMatriculaUsuario(matricula));
    }
}

package com.br.sga.service;

import com.br.sga.domain.Coordenador;
import com.br.sga.repository.CoordenadorRepository;
import com.br.sga.service.dto.CoordenadorDTO;
import com.br.sga.service.dto.UsuarioDTO;
import com.br.sga.service.mapper.CoordenadorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoordenadorService {

    private final CoordenadorRepository repository;
    private final CoordenadorMapper mapper;
    private final UsuarioService usuarioService;

    public List<CoordenadorDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public UsuarioDTO buscar(String matricula) {
        usuarioService.validarMatriculaUsuario(matricula);
        return usuarioService.buscarUsuarioPorMatricula(matricula);
    }

    private Coordenador buscarPorId(String matricula) {
        return repository.findById(matricula).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordenador não encontrada"));
    }

    public CoordenadorDTO salvar(CoordenadorDTO dto) {
        usuarioService.validarMatriculaUsuario(dto.getMatricula());
        validarCoordenadorPossuiCadastro(dto.getMatricula());
        repository.criarCoordenador(dto.getMatricula());
        repository.adicionarRoleCoordenador(dto.getMatricula());
        return dto;
    }

    public void deletar(String id) {
        repository.deletarCoordenador(id);
        repository.deletarRoleCoordenador(id);
    }

    private void validarCoordenadorPossuiCadastro(String matricula) {
        if(repository.existsCoordenadorByMatricula(matricula)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coordenador já possui cadastro");
        }
    }
}

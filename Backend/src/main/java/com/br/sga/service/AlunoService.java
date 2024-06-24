package com.br.sga.service;

import com.br.sga.domain.Aluno;
import com.br.sga.repository.AlunoRepository;
import com.br.sga.service.dto.AlunoDTO;
import com.br.sga.service.dto.UsuarioDTO;
import com.br.sga.service.mapper.AlunoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlunoService {
    private final AlunoRepository repository;
    private final AlunoMapper mapper;
    private final UsuarioService usuarioService;

    public List<AlunoDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public UsuarioDTO buscar(String matricula) {
        usuarioService.validarMatriculaUsuario(matricula);
        return usuarioService.buscarUsuarioPorMatricula(matricula);
    }

    private Aluno buscarPorId(String id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }
    public AlunoDTO salvar(AlunoDTO dto) {
        usuarioService.validarMatriculaUsuario(dto.getMatricula());
        validarAlunoPossuiCadastro(dto.getMatricula());
        repository.criarAluno(dto.getMatricula());
        repository.adicionarRoleAluno(dto.getMatricula());
        return dto;
    }

    public void deletar(String matricula) {
        repository.deletarAluno(matricula);
        repository.deletarRoleAluno(matricula);
    }

    private void validarAlunoPossuiCadastro(String matricula) {
        if(repository.existsAlunoByMatricula(matricula)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno já possui cadastro");
        }
    }
}

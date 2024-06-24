package com.br.sga.service;

import com.br.sga.domain.Professor;
import com.br.sga.repository.ProfessorRepository;
import com.br.sga.service.dto.*;
import com.br.sga.service.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfessorService {

    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;
    private final UsuarioService usuarioService;

    public List<ProfessorListagemDTO> buscarTodos() {
        return repository.buscarTodos();
    }

    public UsuarioDTO buscar(String matricula) {
        usuarioService.validarMatriculaUsuario(matricula);
        return usuarioService.buscarUsuarioPorMatricula(matricula);
    }

    public ProfessorDTO buscarProfessor(String matricula) {
        return mapper.toDto(buscarPorId(matricula));
    }

    private Professor buscarPorId(String matricula) {
        return repository.findById(matricula).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
    }
    public ProfessorDTO salvar(ProfessorDTO dto) {
        usuarioService.validarMatriculaUsuario(dto.getMatricula());
        validarProfessorPossuiCadastro(dto.getMatricula());
        repository.criarProfessor(dto);
        repository.adicionarRoleProfessor(dto.getMatricula());
        return dto;
    }

    public ProfessorDTO atualizar(ProfessorDTO dto) {
        repository.atualizarProfessor(dto);
        return dto;
    }

    public void deletar(String matricula) {
        repository.deletarProfessor(matricula);
        repository.deletarRoleProfessor(matricula);
    }

    private void validarProfessorPossuiCadastro(String matricula) {
        if(repository.existsProfessorByMatricula(matricula)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Professor já possui cadastro");
        }
    }

    public List<DropdownStringDTO> buscarDropdownProfessoresCoordenadores() {
        return repository.buscarDropdownProfessoresECoordenadores();
    }

    public List<DropdownProfessorDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }

    public String buscarMatriculaProfessorPorRFID(String rfid) {
        return repository.buscarMatriculaProfessorPorRFID(rfid);
    }
}

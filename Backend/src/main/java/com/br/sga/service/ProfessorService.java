package com.br.sga.service;

import com.br.sga.domain.Professor;
import com.br.sga.repository.ProfessorRepository;
import com.br.sga.service.dto.ProfessorDTO;
import com.br.sga.service.dto.ProfessorListagemDTO;
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

    public List<ProfessorListagemDTO> buscarTodos() {
        return repository.buscarTodos();
    }

    public ProfessorDTO buscar(Long id) {
        return mapper.toDto(buscarPorId(id));
    }

    private Professor buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
    }
    public ProfessorDTO salvar(ProfessorDTO dto) {
        Professor professor = mapper.toEntity(dto);
        repository.save(professor);
        return mapper.toDto(professor);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

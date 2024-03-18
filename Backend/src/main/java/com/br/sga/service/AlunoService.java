package com.br.sga.service;

import com.br.sga.domain.Aluno;
import com.br.sga.repository.AlunoRepository;
import com.br.sga.service.dto.AlunoDTO;
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

    public List<AlunoDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public AlunoDTO buscar(String id) {
        return mapper.toDto(buscarPorId(id));
    }

    private Aluno buscarPorId(String id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }
    public AlunoDTO salvar(AlunoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}

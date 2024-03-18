package com.br.sga.service;

import com.br.sga.ApplicationException.EmptyListException;
import com.br.sga.ApplicationException.RecordNotFoundException;
import com.br.sga.domain.Aluno;
import com.br.sga.repository.AlunoRepository;
import com.br.sga.service.dto.AlunoDTO;
import com.br.sga.service.mapper.AlunoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlunoService {
    private final AlunoRepository repository;
    private final AlunoMapper mapper;

    public List<AlunoDTO> buscarTodos() {
        List<Aluno> resultList = repository.findAll();

        if(resultList.isEmpty()) {
            throw new EmptyListException();
        }
        return mapper.toDto(resultList);
    }

    public AlunoDTO buscar(Long id) {
        Optional<Aluno> aluno = repository.findById(id);

        if(aluno.isEmpty()) {
            throw new RecordNotFoundException(id);
        }

        return mapper.toDto(aluno.get());
    }

    public AlunoDTO salvar(AlunoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public boolean deletar(Long id) {
        if(!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}

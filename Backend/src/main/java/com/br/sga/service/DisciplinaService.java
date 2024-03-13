package com.br.sga.service;

import com.br.sga.domain.Disciplina;
import com.br.sga.repository.DisciplinaRepository;
import com.br.sga.service.dto.DisciplinaDTO;
import com.br.sga.service.mapper.DisciplinaMapper;
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
public class DisciplinaService {
    private final DisciplinaRepository repository;
    private final DisciplinaMapper mapper;

    public List<DisciplinaDTO> buscarTodos() {
        List<Disciplina> resultList = repository.findAll();

        if(resultList.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toDto(resultList);
    }

    public DisciplinaDTO buscar(Long id) {
        Optional<Disciplina> disciplina = repository.findById(id);

        if(disciplina.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado");
        }
        return mapper.toDto(disciplina.get());
    }

    public DisciplinaDTO salvar(DisciplinaDTO dto) {
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

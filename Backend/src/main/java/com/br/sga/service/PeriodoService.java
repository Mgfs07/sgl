package com.br.sga.service;

import com.br.sga.domain.Periodo;
import com.br.sga.repository.PeriodoRepository;
import com.br.sga.service.dto.PeriodoDTO;
import com.br.sga.service.mapper.PeriodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PeriodoService {

    private final PeriodoRepository repository;
    private final PeriodoMapper mapper;

    public List<PeriodoDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public PeriodoDTO buscar(Long id) {
        return mapper.toDto(buscarPorId(id));
    }

    private Periodo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Período não encontrado"));
    }

    public PeriodoDTO salvar(PeriodoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

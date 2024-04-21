package com.br.sga.service;

import com.br.sga.domain.Coordenador;
import com.br.sga.repository.CoordenadorRepository;
import com.br.sga.service.dto.CoordenadorDTO;
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

    public List<CoordenadorDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public CoordenadorDTO buscar(Long id) {
        return mapper.toDto(buscarPorId(id));
    }

    private Coordenador buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordenador não encontrada"));
    }

    public CoordenadorDTO salvar(CoordenadorDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

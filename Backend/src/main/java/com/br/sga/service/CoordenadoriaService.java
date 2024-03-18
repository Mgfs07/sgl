package com.br.sga.service;

import com.br.sga.domain.Coordenadoria;
import com.br.sga.repository.CoordenadoriaRepository;
import com.br.sga.service.dto.CoordenadoriaDTO;
import com.br.sga.service.mapper.CoordenadoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoordenadoriaService {

    private final CoordenadoriaRepository repository;
    private final CoordenadoriaMapper mapper;

    public List<CoordenadoriaDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public CoordenadoriaDTO buscar(Integer id) {
        return mapper.toDto(buscarPorId(id));
    }

    private Coordenadoria buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordenadoria não encontrado"));
    }

    public CoordenadoriaDTO salvar(CoordenadoriaDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}

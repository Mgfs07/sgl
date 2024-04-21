package com.br.sga.service;

import com.br.sga.domain.Local;
import com.br.sga.repository.LocalRepository;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.LocalDTO;
import com.br.sga.service.dto.LocalListagemProjection;
import com.br.sga.service.mapper.LocalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalService {
    
    private final LocalRepository repository;
    private final LocalMapper mapper;

    public List<LocalListagemProjection> buscarTodos() {
        return repository.buscarTodos();
    }

    public LocalDTO buscar(Long id) {
        return mapper.toDto(buscarPorId(id));
    }

    private Local buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Local não encontrado"));
    }
    public LocalDTO salvar(LocalDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<DropdownDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }
}

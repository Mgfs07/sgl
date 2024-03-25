package com.br.sga.service;

import com.br.sga.domain.Equipamento;
import com.br.sga.repository.EquipamentoRepository;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.EquipamentoDTO;
import com.br.sga.service.mapper.EquipamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipamentoService {

    private final EquipamentoRepository repository;
    private final EquipamentoMapper mapper;

    public List<EquipamentoDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public EquipamentoDTO buscar(Long id) {
        return mapper.toDto(buscarPorid(id));
    }

    private Equipamento buscarPorid(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));
    }

    public EquipamentoDTO salvar(EquipamentoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<DropdownDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }
}

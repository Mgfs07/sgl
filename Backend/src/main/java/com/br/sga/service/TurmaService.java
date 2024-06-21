package com.br.sga.service;

import com.br.sga.repository.TurmaRepository;
import com.br.sga.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TurmaService {

    private final TurmaRepository repository;
    public List<DropdownDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }
}

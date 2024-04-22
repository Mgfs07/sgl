package com.br.sga.service;

import com.br.sga.repository.AulaRepository;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.HorariosAula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AulaService {

    private final AulaRepository repository;


    public List<HorariosAula> buscarHorariosAulaAluno(String matricula) {
        return repository.buscarHorariosAulaAluno(matricula);
    }

    public List<DropdownDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }


}

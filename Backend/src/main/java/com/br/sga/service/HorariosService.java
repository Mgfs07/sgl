package com.br.sga.service;

import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.FIltroDTO;
import com.br.sga.service.dto.HorariosAula2;
import com.br.sga.service.dto.Teste;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HorariosService {
    
    private final TurmaService turmaService;
    private final AulaService alunoService;

    public List<DropdownDTO> buscarDropdownTurma() {
        return turmaService.buscarDropdown();
    }

    public List<HorariosAula2> buscarHorariosAulaAluno2(String matricula) {
        return alunoService.buscarHorariosAulaAluno2(matricula);
    }

    public List<Teste> buscarHorariosAula() {
        return alunoService.buscarHorarios();
    }

    public void chamar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }


}

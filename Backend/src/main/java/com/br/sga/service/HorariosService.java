package com.br.sga.service;

import com.br.sga.domain.enums.TipoAtorBuscaEnum;
import com.br.sga.service.dto.*;
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
    private final AulaService aulaService;

    public List<DropdownDTO> buscarDropdownTurma() {
        return turmaService.buscarDropdown();
    }

    public List<HorariosAula2> buscarHorariosAulaAluno2(String matricula) {
        return aulaService.buscarHorariosAulaAluno2(matricula);
    }

    public List<HorarioDTO> buscarHorariosAula() {
        return aulaService.buscarHorariosAluno("123");
    }

    public void chamar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }


    public List<HorarioDTO> buscarHorarios(HorarioFiltroDTO horarioFiltroDTO) {
        if (horarioFiltroDTO.getTipoAtorBusca().equals(TipoAtorBuscaEnum.ALUNO)){
            return aulaService.buscarHorariosAluno(horarioFiltroDTO.getMatricula());
        }
        if (horarioFiltroDTO.getTipoAtorBusca().equals(TipoAtorBuscaEnum.PROFESSOR)) {
            return aulaService.buscarHorariosProfessor(horarioFiltroDTO);
        }
        if (horarioFiltroDTO.getTipoAtorBusca().equals(TipoAtorBuscaEnum.TURMA)) {
            return aulaService.buscarHorariosTurma(horarioFiltroDTO.getIdTurma());
        }
        return null;
    }
}

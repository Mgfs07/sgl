package com.br.sga.service;

import com.br.sga.domain.enums.TipoAtorBuscaEnum;
import com.br.sga.service.dto.*;
import lombok.RequiredArgsConstructor;
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

    public List<HorarioDTO> buscarHorariosAula() {
        return alunoService.buscarHorarios("123");
    }


    public List<HorarioDTO> buscarHorarios(HorarioFiltroDTO horarioFiltroDTO) {
        if (horarioFiltroDTO.getTipoAtorBusca().equals(TipoAtorBuscaEnum.ALUNO)){
            return alunoService.buscarHorarios(horarioFiltroDTO.getMatricula());
        }
        if (horarioFiltroDTO.getTipoAtorBusca().equals(TipoAtorBuscaEnum.PROFESSOR)) {

        }


        return null;
    }
}

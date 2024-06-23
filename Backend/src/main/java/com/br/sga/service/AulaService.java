package com.br.sga.service;

import com.br.sga.repository.AulaRepository;
import com.br.sga.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class AulaService {

    private final AulaRepository repository;
    private final ProfessorService professorService;


    public List<HorariosAula> buscarHorariosAulaAluno(String matricula) {
        return repository.buscarHorariosAulaAluno(matricula);
    }

    public List<HorariosAula2> buscarHorariosAulaAluno2(String matricula) {
        return repository.buscarHorariosAulaAluno2(matricula);
    }

    public List<HorarioDTO> buscarHorariosAluno(String matricula) {
        List<HorarioDTO> horario = repository.buscarInformacoesAluno(matricula);
        horario.forEach(item -> item.setAulas(repository.buscarHorariosAulaAluno2(matricula)));
        return horario;
    }

    public List<HorarioDTO> buscarHorariosProfessor(HorarioFiltroDTO horarioFiltroDTO) {
        String matricula;
        if(Objects.nonNull(horarioFiltroDTO.getRfId())){
            matricula = professorService.buscarMatriculaProfessorPorRFID(horarioFiltroDTO.getRfId());
            verificarMatriculaValida(matricula);
        } else {
            matricula = horarioFiltroDTO.getMatricula();
        }
        List<HorarioDTO> horario = repository.buscarInformacoesProfessor(matricula);
        horario.forEach(item -> item.setAulas(repository.buscarHorariosAulaProfessor(matricula)));
        return horario;
    }

    private void verificarMatriculaValida(String matricula) {
        if(Objects.isNull(matricula)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RFID inválido");
        }
    }

    public List<HorarioDTO> buscarHorariosTurma(Long idTurma) {
        List<HorarioDTO> horario;
        if (Objects.isNull(idTurma)){
            horario  = repository.buscarInformacoesTurma();
        } else {
            horario = repository.buscarInformacoesTurma(idTurma);
        }
        horario.forEach(item -> item.setAulas(repository.buscarHorariosAulaTurma(item.getIdTurma())));
        return horario;
    }

    public List<DropdownDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }

    public void salvarLocalAula(Long idLocal, Long idAula) {
        repository.salvarLocalAula(idLocal, idAula);
    }

    public List<AulaListDTO> buscarAulasParaAlocacao() {
        return repository.buscarAulasParaAlocacao();
    }



}

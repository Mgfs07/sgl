package com.br.sga.service;

import com.br.sga.repository.AulaRepository;
import com.br.sga.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

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

    public HorariosAula2 buscarProximaAulaAluno(String matricula) {
        DayOfWeek day = LocalDateTime.now().getDayOfWeek();

        HorariosAula2 proximaAula = repository.buscarProximaAula(matricula, getDiaDaSemanaInt(day));
        return proximaAula;
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

    public void salvarLocalAula(AlocacaoLocalAulaDTO alocacaoLocalAulaDTO) {
        repository.salvarLocalAula(alocacaoLocalAulaDTO.getIdLocal(), alocacaoLocalAulaDTO.getIdAula());
    }

    public List<AulaListDTO> buscarAulasParaAlocacao(Long idDisciplina) {
        return repository.buscarAulasParaAlocacao(idDisciplina);
    }

    public AulaDTO buscarAulaPorId(Long idAula){
        return repository.buscarAulaPorId(idAula);
    }

    public Long getDiaDaSemanaInt(DayOfWeek day) {
        switch (day) {
            case SUNDAY:
                return 1L;
            case MONDAY:
                return 2L;
            case TUESDAY:
                return 3L;
            case WEDNESDAY:
                return 4L;
            case THURSDAY:
                return 5L;
            case FRIDAY:
                return 6L;
            case SATURDAY:
                return 7L;
            default:
                throw new IllegalArgumentException("Dia da semana inválido: " + day);
        }
    }


}

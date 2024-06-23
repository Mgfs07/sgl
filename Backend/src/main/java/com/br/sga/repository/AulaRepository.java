package com.br.sga.repository;

import com.br.sga.domain.Aula;
import com.br.sga.service.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    @Query("SELECT NEW com.br.sga.service.dto.DropdownDTO(" +
            "a.id, " +
            "concat(a.nome,' - ', a.professor.nome, ' - ', a.horaInicio, ' às ', a.horaFim, ' - ', a.diaSemana.nome) ) " +
            "FROM " +
            "   Aula a")
    List<DropdownDTO> buscarDropdown();

    @Query("Select new com.br.sga.service.dto.HorariosAula(" +
            "a.id, " +
            "a.disciplina.nome," +
            "a.professor.nome," +
            "a.horaInicio," +
            "a.horaFim," +
            "a.diaSemana.nome," +
            "l.nome " +
            ") " +
            "   From " +
            "       Aula a " +
            "       Join AlunoAula aa on aa.id.idAula = a.id " +
            "       left join a.local l " +
            "   WHERE" +
            "       aa.id.matriculaAluno = :matricula" +
            "   order by " +
            "       a.diaSemana.id")
    List<HorariosAula> buscarHorariosAulaAluno(String matricula);


    @Query("Select new com.br.sga.service.dto.HorariosAula2(" +
            "a.id," +
            "a.nome, " +
            "a.disciplina.nome," +
            "a.professor.nome," +
            "a.horaInicio," +
            "a.horaFim," +
            "a.diaSemana.id," +
            "l.nome " +
            ") " +
            "   From " +
            "       Aula a " +
            "       Join AlunoAula aa on aa.id.idAula = a.id " +
            "       left join a.local l " +
            "   WHERE" +
            "       aa.id.matriculaAluno = :matricula" +
            "   order by " +
            "       a.diaSemana.id")
    List<HorariosAula2> buscarHorariosAulaAluno2(String matricula);

    @Query("Select new com.br.sga.service.dto.HorariosAula2(" +
            "a.id," +
            "a.nome, " +
            "a.disciplina.nome," +
            "a.professor.nome," +
            "a.horaInicio," +
            "a.horaFim," +
            "a.diaSemana.id," +
            "l.nome " +
            ") " +
            "   From " +
            "       Aula a " +
            "       Join a.professor p " +
            "       left join a.local l " +
            "   WHERE" +
            "       p.matricula = :matricula and (local_date between a.periodo.dataInicio and a.periodo.dataFim)" +
            "   order by " +
            "       a.diaSemana.id ")
    List<HorariosAula2> buscarHorariosAulaProfessor(String matricula);

    @Query("Select new com.br.sga.service.dto.HorariosAula2(" +
            "a.id," +
            "a.nome, " +
            "a.disciplina.nome," +
            "a.professor.nome," +
            "a.horaInicio," +
            "a.horaFim," +
            "a.diaSemana.id," +
            "l.nome " +
            ") " +
            "   From " +
            "       Aula a " +
            "       left join a.local l " +
            "   WHERE" +
            "       a.turma.id = :idTurma and (local_date between a.periodo.dataInicio and a.periodo.dataFim)" +
            "   order by " +
            "       a.diaSemana.id ")
    List<HorariosAula2> buscarHorariosAulaTurma(Long idTurma);


    @Query("Select DISTINCT new com.br.sga.service.dto.HorarioDTO(" +
            "u.nome," +
            "a.periodo.descricao " +
            ") " +
            "   From " +
            "       Aula a " +
            "       Join AlunoAula aa on aa.id.idAula = a.id " +
            "       join Usuario u on u.matricula = aa.id.matriculaAluno" +
            "   WHERE" +
            "       aa.id.matriculaAluno = :matricula and (local_date between a.periodo.dataInicio and a.periodo.dataFim)")
    List<HorarioDTO> buscarInformacoesAluno(String matricula);


    @Query("Select DISTINCT new com.br.sga.service.dto.HorarioDTO(" +
            "p.nome," +
            "a.periodo.descricao " +
            ") " +
            "   From " +
            "       Aula a" +
            "       Join a.professor p " +
            "   WHERE" +
            "       p.matricula = :matricula and (local_date between a.periodo.dataInicio and a.periodo.dataFim)")
    List<HorarioDTO> buscarInformacoesProfessor(String matricula);


    @Query("Select DISTINCT new com.br.sga.service.dto.HorarioDTO(" +
            "a.turma.nome," +
            "a.periodo.descricao," +
            "a.turma.id" +
            ") " +
            "   From " +
            "       Aula a " +
            "   WHERE" +
            "       a.turma.id = :idTurma and (local_date between a.periodo.dataInicio and a.periodo.dataFim)")
    List<HorarioDTO> buscarInformacoesTurma(Long idTurma);

    @Query("Select DISTINCT new com.br.sga.service.dto.HorarioDTO(" +
            "a.turma.nome," +
            "a.periodo.descricao," +
            "a.turma.id " +
            ") " +
            "   From " +
            "       Aula a " +
            "   WHERE" +
            "       local_date between a.periodo.dataInicio and a.periodo.dataFim")
    List<HorarioDTO> buscarInformacoesTurma();

    @Modifying
    @Query("UPDATE Aula SET local.id = :idLocal WHERE id = :idAula")
    void salvarLocalAula(Long idLocal, Long idAula);

    @Query("select new com.br.sga.service.dto.AulaListDTO( " +
            "a.id, " +
            "a.disciplina.nome, " +
            "a.horaInicio, " +
            "a.horaFim, " +
            "a.professor.nome, " +
            "l.nome " +
            ") From " +
            "       Aula a " +
            "       left join a.local l where (:idDisciplina is null or a.disciplina.id = :idDisciplina) " +
            " Order by a.disciplina.id ")
    List<AulaListDTO> buscarAulasParaAlocacao(Long idDisciplina);

    @Query("select new com.br.sga.service.dto.AulaDTO( " +
            "a.id, " +
            "a.nome, " +
            "a.horaInicio, " +
            "a.horaFim, " +
            "a.local.id, " +
            "a.professor.nome, " +
            "a.diaSemana.nome, " +
            "a.diaSemana.id, " +
            "a.disciplina.nome " +
            ") From " +
            "       Aula a " +
            "       left join a.local l " +
            "where a.id = :idAula Order by a.disciplina.id ")
    AulaDTO buscarAulaPorId(Long idAula);


}

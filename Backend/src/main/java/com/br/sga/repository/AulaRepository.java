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


    @Query("Select DISTINCT new com.br.sga.service.dto.Teste(" +
            "u.nome," +
            "u.nome, " +
            "a.periodo.descricao ) " +
            "   From " +
            "       Aula a " +
            "       Join AlunoAula aa on aa.id.idAula = a.id " +
            "       join Usuario u on u.matricula = aa.id.matriculaAluno" +
            "   WHERE" +
            "       aa.id.matriculaAluno = :matricula and a.periodo.id = :periodo")
    List<HorarioDTO> buscarHorarios(String matricula, Long periodo);

    @Modifying
    @Query("UPDATE Aula SET local.id = :idLocal WHERE id = :idAula")
    void salvarLocalAula(Long idLocal, Long idAula);

//    @Query("select new com.br.sga.service.dto.AulaDTO2( " +
//            "a.id, " +
//            "a.horaInicio, " +
//            "a.horaFim, " +
//            "a.turma, " +
//            "a.periodo.descricao, " +
//            "a.diaSemana.nome, " +
//            "a.local.id, " +
//            "l.nome" +
//            ") From " +
//            "       Aula a " +
//            "       left join a.local l" +
//            " Where " +
//            "       a.id = :idAula")
//    AulaDTO2 buscarAulaParaAlocacao(Long idAula);
}

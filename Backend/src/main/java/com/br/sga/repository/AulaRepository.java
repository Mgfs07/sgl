package com.br.sga.repository;

import com.br.sga.domain.Aula;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.HorariosAula;
import org.springframework.data.jpa.repository.JpaRepository;
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
            "a.nome," +
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
}

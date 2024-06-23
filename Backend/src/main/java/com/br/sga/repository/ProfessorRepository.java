package com.br.sga.repository;

import com.br.sga.domain.Professor;
import com.br.sga.service.dto.DropdownProfessorDTO;
import com.br.sga.service.dto.ProfessorListagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("SELECT NEW com.br.sga.service.dto.ProfessorListagemDTO(" +
            " P.matricula," +
            " P.nome," +
            " C.nome) " +
            "From Professor P LEFT JOIN P.coordenadoria C")
    List<ProfessorListagemDTO> buscarTodos();

    @Query("SELECT NEW com.br.sga.service.dto.DropdownProfessorDTO(" +
            "p.matricula, " +
            "p.nome) " +
            "FROM " +
            "   Professor p")
    List<DropdownProfessorDTO> buscarDropdown();


    @Query("select p.matricula From Professor p where p.rfid = :rfid")
    String buscarMatriculaProfessorPorRFID(String rfid);

}

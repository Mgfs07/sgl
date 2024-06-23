package com.br.sga.repository;

import com.br.sga.domain.Aula;
import com.br.sga.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Aula, Long> {

    @Query("SELECT NEW com.br.sga.service.dto.DropdownDTO(" +
            "t.id, " +
            "t.nome )" +
            "FROM " +
            "   Turma t")
    List<DropdownDTO> buscarDropdown();

}

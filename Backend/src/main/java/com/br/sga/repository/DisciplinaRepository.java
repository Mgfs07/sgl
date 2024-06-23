package com.br.sga.repository;

import com.br.sga.domain.Disciplina;
import com.br.sga.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    @Query("SELECT distinct NEW com.br.sga.service.dto.DropdownDTO(" +
            "d.id, " +
            "concat(a.nome, ' - ' ,d.nome) ) FROM Disciplina d left join Aula a on a.disciplina.id = d.id")
    List<DropdownDTO> buscarDropdown();
}

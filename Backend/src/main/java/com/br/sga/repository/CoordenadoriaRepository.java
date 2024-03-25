package com.br.sga.repository;

import com.br.sga.domain.Coordenadoria;
import com.br.sga.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoordenadoriaRepository extends JpaRepository<Coordenadoria, Long> {

    @Query("SELECT NEW com.br.sga.service.dto.DropdownDTO(" +
                "C.id, " +
                "C.nome) " +
            "FROM " +
            "   Coordenadoria C")
    List<DropdownDTO> buscarDropdown();
}

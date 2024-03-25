package com.br.sga.repository;

import com.br.sga.domain.Equipamento;
import com.br.sga.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    @Query("SELECT NEW com.br.sga.service.dto.DropdownDTO(" +
            "E.id, " +
            "E.nome) FROM Equipamento E")
    List<DropdownDTO> buscarDropdown();
}

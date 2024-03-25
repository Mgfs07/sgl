package com.br.sga.repository;

import com.br.sga.domain.Local;
import com.br.sga.service.dto.LocalListagemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local, Long> {

    @Query(value = "SELECT " +
                "L.id as id, " +
                "L.nome as nome," +
                "L.capacidade as capacidade," +
                "string_agg(E.nome, ', ' order by E.nome) as equipamentos " +
            "FROM " +
            "    Local L" +
            "    LEFT JOIN local_equipamento le on le.id_local = L.id" +
            "    LEFT JOIN Equipamento E on E.id = le.id_equipamento " +
            "GROUP BY " +
            "   L.id," +
            "   L.nome," +
            "   L.capacidade", nativeQuery = true)
    List<LocalListagemProjection> buscarTodos();

}

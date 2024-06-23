package com.br.sga.repository;

import com.br.sga.domain.AuditoriaEvento;
import com.br.sga.service.dto.AuditoriaEventoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditoriaEventoRepository extends JpaRepository<AuditoriaEvento, Long> {

    @Query("SELECT NEW com.br.sga.service.dto.AuditoriaEventoDTO(" +
            "ae.id, " +
            "ae.acao, " +
            "ae.data, " +
            "ae.usuarioSolicitante.matricula, " +
            "ae.usuarioLogado.matricula, " +
            "ae.usuarioLogado.nome, " +
            "ae.evento.id) " +
            "FROM AuditoriaEvento ae ")
    List<AuditoriaEventoDTO> buscarTodasAuditoriasListDTO();
}

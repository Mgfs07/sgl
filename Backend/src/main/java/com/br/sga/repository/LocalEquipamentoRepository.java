package com.br.sga.repository;

import com.br.sga.domain.LocalEquipamento;
import com.br.sga.domain.pk.LocalEquipamentoPK;
import com.br.sga.service.dto.LocalEquipamentoListagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalEquipamentoRepository extends JpaRepository<LocalEquipamento, LocalEquipamentoPK> {

    @Query("SELECT NEW com.br.sga.service.dto.LocalEquipamentoListagemDTO(" +
            "le.id.idLocal," +
            "le.id.idEquipamento," +
            "le.quantidade," +
            "e.nome) " +
            "FROM " +
            "   LocalEquipamento le " +
            "   join Equipamento e on e.id = le.id.idEquipamento " +
            "where " +
            "   le.id.idLocal = :idLocal")
    List<LocalEquipamentoListagemDTO> buscarEquipamentosLocalPorId(Long idLocal);

    void deleteLocalEquipamentoById_IdLocalAndId_IdEquipamento(Long idLocal, Long idEquipamento);
}

package com.br.sga.repository;

import com.br.sga.domain.Evento;
import com.br.sga.service.dto.DataHoraDTO;
import com.br.sga.service.dto.EventoListagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("Select new com.br.sga.service.dto.EventoListagemDTO(" +
            " e.id," +
            " e.descricao," +
            " e.horaInicio, " +
            " e.horaFim," +
            " e.data," +
            " e.local.nome) From Evento e")
    List<EventoListagemDTO> buscarTodos();

//    @Query("select " +
//            "   case when #{#dataHoraDTO.horaInicio} between e.horaInicio and e.horaFim then true else false end " +
//            "from " +
//            "    Evento e " +
//            "where " +
//            "    e.data = #{#dataHoraDTO.data}")
//    Boolean verificarEventoMesmoHorario(DataHoraDTO dataHoraDTO);

}

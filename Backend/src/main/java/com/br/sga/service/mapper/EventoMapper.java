package com.br.sga.service.mapper;

import com.br.sga.domain.Evento;
import com.br.sga.service.dto.EventoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

    @Override
    @Mapping(source = "idLocal", target = "local.id")
    @Mapping(source = "matriculaSolicitante", target = "usuarioSolicitante.matricula")
    Evento toEntity(EventoDTO dto);

    @Override
    @InheritInverseConfiguration
    EventoDTO toDto(Evento entity);
}

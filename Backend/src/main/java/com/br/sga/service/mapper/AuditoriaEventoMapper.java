package com.br.sga.service.mapper;

import com.br.sga.domain.AuditoriaEvento;
import com.br.sga.service.dto.AuditoriaEventoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditoriaEventoMapper extends EntityMapper<AuditoriaEventoDTO, AuditoriaEvento> {

    @Override
    @Mapping(source = "usuarioSolicitante.matricula", target = "matriculaSolicitante")
    @Mapping(source = "usuarioLogado.matricula", target = "matriculaUsuarioLogado")
    @Mapping(source = "evento.id", target = "idEvento")
    AuditoriaEventoDTO toDto(AuditoriaEvento auditoriaEvento);

    @Override
    @InheritInverseConfiguration
    AuditoriaEvento toEntity(AuditoriaEventoDTO auditoriaEventoDTO);
}

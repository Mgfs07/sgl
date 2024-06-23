package com.br.sga.service.mapper;

import com.br.sga.domain.Coordenador;
import com.br.sga.domain.Roles;
import com.br.sga.service.dto.CoordenadorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {Roles.class})
public interface CoordenadorMapper extends EntityMapper<CoordenadorDTO, Coordenador> {

    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Coordenador toEntity(CoordenadorDTO dto);
}

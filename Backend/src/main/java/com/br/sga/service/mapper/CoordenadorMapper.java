package com.br.sga.service.mapper;

import com.br.sga.domain.Coordenador;
import com.br.sga.service.dto.CoordenadorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper extends EntityMapper<CoordenadorDTO, Coordenador> {
}

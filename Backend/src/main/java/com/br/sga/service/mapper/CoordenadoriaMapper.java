package com.br.sga.service.mapper;

import com.br.sga.domain.Coordenadoria;
import com.br.sga.service.dto.CoordenadoriaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordenadoriaMapper extends EntityMapper<CoordenadoriaDTO, Coordenadoria> {
}

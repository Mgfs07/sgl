package com.br.sga.service.mapper;

import com.br.sga.domain.Periodo;
import com.br.sga.service.dto.PeriodoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeriodoMapper extends EntityMapper<PeriodoDTO, Periodo> {
}

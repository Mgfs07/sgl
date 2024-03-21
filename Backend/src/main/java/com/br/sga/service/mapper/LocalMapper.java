package com.br.sga.service.mapper;

import com.br.sga.domain.Local;
import com.br.sga.service.dto.LocalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalMapper extends EntityMapper<LocalDTO, Local> {
}

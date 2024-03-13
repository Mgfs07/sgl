package com.br.sga.service.mapper;

import com.br.sga.domain.Disciplina;
import com.br.sga.service.dto.DisciplinaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper extends EntityMapper<DisciplinaDTO, Disciplina>{
}

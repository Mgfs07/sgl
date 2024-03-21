package com.br.sga.service.mapper;

import com.br.sga.domain.Professor;
import com.br.sga.service.dto.ProfessorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper extends EntityMapper<ProfessorDTO, Professor>{

    @Mapping(source = "coordenadoria.id", target = "idCoordenadoria")
    ProfessorDTO toDto(Professor entity);

    @Mapping(target = "coordenadoria", expression = "java(dto.getIdCoordenadoria() != null ? new Coordenadoria(dto.getIdCoordenadoria()) : null)")
    Professor toEntity(ProfessorDTO dto);
}

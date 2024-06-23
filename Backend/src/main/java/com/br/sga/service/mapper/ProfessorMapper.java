package com.br.sga.service.mapper;

import com.br.sga.domain.Professor;
import com.br.sga.domain.Roles;
import com.br.sga.service.dto.ProfessorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {Roles.class})
public interface ProfessorMapper extends EntityMapper<ProfessorDTO, Professor>{

    @Mapping(source = "coordenadoria.id", target = "idCoordenadoria")
    ProfessorDTO toDto(Professor entity);

    @Mapping(target = "coordenadoria", expression = "java(dto.getIdCoordenadoria() != null ? new Coordenadoria(dto.getIdCoordenadoria()) : null)")
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Professor toEntity(ProfessorDTO dto);
}

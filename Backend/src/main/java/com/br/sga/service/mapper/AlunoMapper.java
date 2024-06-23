package com.br.sga.service.mapper;

import com.br.sga.domain.Aluno;
import com.br.sga.domain.Professor;
import com.br.sga.service.dto.AlunoDTO;
import com.br.sga.service.dto.ProfessorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper extends EntityMapper<AlunoDTO, Aluno> {

    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Aluno toEntity(AlunoDTO dto);
}

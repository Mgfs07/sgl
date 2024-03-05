package com.br.sga.service.mapper;

import com.br.sga.domain.Aluno;
import com.br.sga.service.dto.AlunoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper extends EntityMapper<AlunoDTO, Aluno>{
}

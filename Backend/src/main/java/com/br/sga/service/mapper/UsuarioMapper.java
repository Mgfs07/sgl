package com.br.sga.service.mapper;

import com.br.sga.domain.Usuario;
import com.br.sga.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {
}

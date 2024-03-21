package com.br.sga.service.mapper;

import com.br.sga.domain.LocalEquipamento;
import com.br.sga.service.dto.LocalEquipamentoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocalEquipamentoMapper extends EntityMapper<LocalEquipamentoDTO, LocalEquipamento> {

    @Mapping(target = "id.idLocal", source = "idLocal")
    @Mapping(target = "id.idEquipamento", source = "idEquipamento")
    LocalEquipamento toEntity(LocalEquipamentoDTO dto);

    @InheritInverseConfiguration
    LocalEquipamentoDTO toDto(LocalEquipamento entity);
}

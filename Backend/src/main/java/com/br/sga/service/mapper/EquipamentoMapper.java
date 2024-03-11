package com.br.sga.service.mapper;

import com.br.sga.domain.Equipamento;
import com.br.sga.service.dto.EquipamentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper extends EntityMapper<EquipamentoDTO, Equipamento>{
}

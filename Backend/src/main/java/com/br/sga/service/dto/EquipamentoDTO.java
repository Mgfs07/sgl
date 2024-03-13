package com.br.sga.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipamentoDTO (
        Long id,

        @NotBlank(message = "Equipamento necessita ter um nome")
        @Size(max = 255)
        String nome
) {}

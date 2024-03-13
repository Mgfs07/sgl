package com.br.sga.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaDTO (
        Long id,

        @NotNull
        @NotBlank
        String nome
)
{
}

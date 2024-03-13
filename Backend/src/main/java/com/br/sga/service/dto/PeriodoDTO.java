package com.br.sga.service.dto;

import java.time.LocalDate;

public record PeriodoDTO (

     Long id,
     String descricao,
     LocalDate dataInicio,
     LocalDate dataFim
){}

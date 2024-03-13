package com.br.sga.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventoDTO(

     Long id,
     String descricao,
     LocalTime horaInicio,
     LocalTime horaFim,
     LocalDate data,
     Long idLocal
){}

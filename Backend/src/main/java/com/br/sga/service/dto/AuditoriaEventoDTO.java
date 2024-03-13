package com.br.sga.service.dto;

import java.time.LocalDate;

public record AuditoriaEventoDTO (

     Long id,
     String acao,
     LocalDate data,
     Long idCoordenador,
     Long idProfessor,
     Long idEvento
){}
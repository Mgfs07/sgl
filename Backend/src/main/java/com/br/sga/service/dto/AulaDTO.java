package com.br.sga.service.dto;

import java.time.LocalTime;

public record AulaDTO(
     Long id,
     String nome,
     LocalTime horaInicio,
     LocalTime horaFim,
     String turma,
     Long idPeriodo,
     Long idProfessor,
     Long idDiaSemana,
     Long idDisciplina,
     Long idLocal

){}

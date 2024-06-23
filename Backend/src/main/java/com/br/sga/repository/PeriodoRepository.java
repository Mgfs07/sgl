package com.br.sga.repository;

import com.br.sga.domain.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {


    @Query("select p.id from Periodo p where local_date between p.dataInicio and p.dataFim")
    Long buscarPeriodoVigente();
}

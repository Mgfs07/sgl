package com.br.sga.repository;

import com.br.sga.domain.LocalEquipamento;
import com.br.sga.domain.pk.LocalEquipamentoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalEquipamentoRepository extends JpaRepository<LocalEquipamento, LocalEquipamentoPK> {
}

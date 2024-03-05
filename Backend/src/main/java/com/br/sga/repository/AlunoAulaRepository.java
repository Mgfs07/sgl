package com.br.sga.repository;

import com.br.sga.domain.AlunoAula;
import com.br.sga.domain.pk.AlunoAulaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoAulaRepository extends JpaRepository<AlunoAula, AlunoAulaPK> {
}

package com.br.sga.repository;

import com.br.sga.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, String> {

    Boolean existsAlunoByMatricula(String matricula);

    @Modifying
    @Query(value = "insert into Aluno(matricula) values (:matricula)", nativeQuery = true)
    void criarAluno(String matricula);

    @Modifying
    @Query(value = "insert into usuario_role(id_role, matricula) values (4, :matricula)", nativeQuery = true)
    void adicionarRoleAluno(String matricula);

    @Modifying
    @Query(value = "delete from Aluno where matricula = :matricula", nativeQuery = true)
    void deletarAluno(String matricula);

    @Modifying
    @Query(value = "delete from usuario_role where matricula = :matricula and id_role = 4", nativeQuery = true)
    void deletarRoleAluno(String matricula);
}

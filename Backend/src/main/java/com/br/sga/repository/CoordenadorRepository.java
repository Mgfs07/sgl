package com.br.sga.repository;

import com.br.sga.domain.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {

    Boolean existsCoordenadorByMatricula(String matricula);

    @Modifying
    @Query(value = "insert into Coordenador(matricula) values (:matricula)", nativeQuery = true)
    void criarCoordenador(String matricula);

    @Modifying
    @Query(value = "insert into Usuario_role(id_role, matricula) values (3, :matricula)", nativeQuery = true)
    void adicionarRoleCoordenador(String matricula);

    @Modifying
    @Query(value = "delete from coordenador where matricula = :matricula", nativeQuery = true)
    void deletarCoordenador(String matricula);

    @Modifying
    @Query(value = "delete from usuario_role where matricula = :matricula and id_role = 3", nativeQuery = true)
    void deletarRoleCoordenador(String matricula);

}

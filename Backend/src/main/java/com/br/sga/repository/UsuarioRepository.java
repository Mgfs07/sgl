package com.br.sga.repository;

import com.br.sga.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.matricula = :matricula")
    Optional<Usuario> findByIdWithRoles(@Param("matricula") String matricula);

    Usuario findByMatricula(String matricula);
}

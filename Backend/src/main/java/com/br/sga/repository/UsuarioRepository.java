package com.br.sga.repository;
import com.br.sga.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

    Usuario findByMatricula(String matricula);

}

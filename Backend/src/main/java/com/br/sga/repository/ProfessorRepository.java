package com.br.sga.repository;

import com.br.sga.domain.Professor;
import com.br.sga.service.dto.DropdownProfessorDTO;
import com.br.sga.service.dto.DropdownStringDTO;
import com.br.sga.service.dto.ProfessorDTO;
import com.br.sga.service.dto.ProfessorListagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, String> {

    @Query("SELECT NEW com.br.sga.service.dto.ProfessorListagemDTO(" +
            " P.matricula," +
            " P.nome," +
            " C.nome) " +
            "From Professor P LEFT JOIN P.coordenadoria C")
    List<ProfessorListagemDTO> buscarTodos();

    @Query("SELECT NEW com.br.sga.service.dto.DropdownProfessorDTO(" +
            "p.matricula, " +
            "p.nome) " +
            "FROM " +
            "   Professor p")
    List<DropdownProfessorDTO> buscarDropdown();


    @Query("select p.matricula From Professor p where p.rfid = :rfid")
    String buscarMatriculaProfessorPorRFID(String rfid);


    @Query("SELECT NEW com.br.sga.service.dto.DropdownStringDTO(" +
            "p.matricula, " +
            "p.nome) " +
            "FROM Professor p " +
            "UNION " +
            "SELECT NEW com.br.sga.service.dto.DropdownStringDTO(" +
            "c.matricula, " +
            "c.nome) " +
            "FROM Coordenador c")
    List<DropdownStringDTO> buscarDropdownProfessoresECoordenadores();


    Boolean existsProfessorByMatricula(String matricula);

    @Modifying
    @Query(value = "insert into Professor(matricula, id_coordenadoria, rfid) values " +
            "(:#{#professorDTO.getMatricula()}, :#{#professorDTO.getIdCoordenadoria()}, :#{#professorDTO.getRfid()} )", nativeQuery = true)
    void criarProfessor(ProfessorDTO professorDTO);

    @Modifying
    @Query(value = "update Professor set " +
            "   id_coordenadoria = :#{#professorDTO.getIdCoordenadoria()}, " +
            "   rfid = :#{#professorDTO.getRfid()} " +
            "where " +
            "   matricula = :#{#professorDTO.getMatricula()}", nativeQuery = true)
    void atualizarProfessor(ProfessorDTO professorDTO);

    @Modifying
    @Query(value = "insert into usuario_role(id_role, matricula) values (2, :matricula)", nativeQuery = true)
    void adicionarRoleProfessor(String matricula);

    @Modifying
    @Query(value = "delete from Professor where matricula = :matricula", nativeQuery = true)
    void deletarProfessor(String matricula);

    @Modifying
    @Query(value = "delete from usuario_role where matricula = :matricula and id_role = 2", nativeQuery = true)
    void deletarRoleProfessor(String matricula);
    
}

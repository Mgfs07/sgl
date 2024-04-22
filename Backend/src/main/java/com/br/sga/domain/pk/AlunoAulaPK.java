package com.br.sga.domain.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoAulaPK implements Serializable {

    @Column(name = "matricula_aluno")
    private String matriculaAluno;

    @Column(name = "id_aula")
    private Long idAula;

}

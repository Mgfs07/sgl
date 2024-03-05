package com.br.sga.domain;

import com.br.sga.domain.pk.AlunoAulaPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "aluno_aula")
@AllArgsConstructor
@NoArgsConstructor
public class AlunoAula {

    @EmbeddedId
    private AlunoAulaPK id;
}

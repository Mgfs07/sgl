package com.br.sga.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "aluno")
@AllArgsConstructor
@NoArgsConstructor
public class Aluno implements Serializable {

    @Id
    @Column(name = "matricula")
    private String matricula;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cod_barra")
    private String codigoBarra;
}

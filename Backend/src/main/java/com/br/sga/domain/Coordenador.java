package com.br.sga.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "coordenador")
@AllArgsConstructor
@NoArgsConstructor
public class Coordenador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coordenador")
    @SequenceGenerator(name = "seq_coordenador", sequenceName = "seq_coordenador", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;
}

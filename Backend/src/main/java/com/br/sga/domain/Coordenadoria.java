package com.br.sga.domain;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "coordenadoria")
@NoArgsConstructor
@AllArgsConstructor
public class Coordenadoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coordenadoria")
    @SequenceGenerator(name = "seq_coordenadoria", sequenceName = "seq_coordenadoria", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    public Coordenadoria(Long id) {
        this.id = id;
    }
}

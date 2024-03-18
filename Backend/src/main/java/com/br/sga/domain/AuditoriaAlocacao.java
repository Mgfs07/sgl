package com.br.sga.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "auditoria_alocacao")
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaAlocacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auditoria_alocacao")
    @SequenceGenerator(name = "seq_auditoria_alocacao", sequenceName = "seq_auditoria_alocacao", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "acao", nullable = false)
    private String acao;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_coordenador")
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_aula", nullable = false)
    private Aula aula;
}

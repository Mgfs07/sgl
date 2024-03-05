package com.br.sga.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
    @SequenceGenerator(name = "seq_auditoria_alocacao", sequenceName = "seq_auditoria_alocacao", initialValue = 8)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "acao")
    private String acao;

    @Column(name = "data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_coordenador")
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;
}

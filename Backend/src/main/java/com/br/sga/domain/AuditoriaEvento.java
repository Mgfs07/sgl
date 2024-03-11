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
@Table(name = "auditoria_evento")
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaEvento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auditoria_evento")
    @SequenceGenerator(name = "seq_auditoria_evento", sequenceName = "seq_auditoria_evento", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "acao", nullable = false)
    private String acao;

    @Column(name = "data")
    private LocalDate data = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_coordenador")
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;
}

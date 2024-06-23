package com.br.sga.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "professor")
public class Professor extends Usuario implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coordenadoria", referencedColumnName = "id")
    private Coordenadoria coordenadoria;

    @Column(name = "rfid")
    private String rfid;

}

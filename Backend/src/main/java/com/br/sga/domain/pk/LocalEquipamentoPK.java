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
public class LocalEquipamentoPK implements Serializable {

    @Column(name = "id_local")
    private Integer idLocal;

    @Column(name = "id_equipamento")
    private Integer idEquipamento;

}

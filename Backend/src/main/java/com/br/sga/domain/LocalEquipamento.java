package com.br.sga.domain;

import com.br.sga.domain.pk.LocalEquipamentoPK;
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
@Table(name = "local_equipamento")
@AllArgsConstructor
@NoArgsConstructor
public class LocalEquipamento {

    @EmbeddedId
    private LocalEquipamentoPK id;
}

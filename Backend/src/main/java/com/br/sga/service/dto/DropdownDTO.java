package com.br.sga.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DropdownDTO implements Serializable {

    private Long value;
    private String label;
}

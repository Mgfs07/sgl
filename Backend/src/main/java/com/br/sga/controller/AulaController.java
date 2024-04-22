package com.br.sga.controller;

import com.br.sga.service.AulaService;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.HorariosAula;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
public class AulaController {
    private final AulaService service;

    @GetMapping("/horarios-aluno/{matricula}")
    public ResponseEntity<List<HorariosAula>> buscarHorariosAulaAluno(@PathVariable String matricula) {
        List<HorariosAula> listagem = service.buscarHorariosAulaAluno(matricula);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DropdownDTO>> buscarTodos() {
        List<DropdownDTO> listagem = service.buscarDropdown();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }
}

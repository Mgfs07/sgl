package com.br.sga.controller;

import com.br.sga.service.ProfessorService;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.DropdownProfessorDTO;
import com.br.sga.service.dto.ProfessorDTO;
import com.br.sga.service.dto.ProfessorListagemDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public ResponseEntity<List<ProfessorListagemDTO>> buscarTodos() {
        List<ProfessorListagemDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPorId(@PathVariable("id") Long id) {
        ProfessorDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> salvar(@Valid @RequestBody ProfessorDTO dto) {
        ProfessorDTO ProfessorDTO = service.salvar(dto);
        return new ResponseEntity<>(ProfessorDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProfessorDTO> atualizar(@Valid @RequestBody ProfessorDTO dto) {
        ProfessorDTO ProfessorDTO = service.salvar(dto);
        return new ResponseEntity<>(ProfessorDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleter(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DropdownProfessorDTO>> buscarDropdown() {
        List<DropdownProfessorDTO> dropdownDTOS = service.buscarDropdown();
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }
}

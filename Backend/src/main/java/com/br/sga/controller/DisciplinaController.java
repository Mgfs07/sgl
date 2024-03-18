package com.br.sga.controller;

import com.br.sga.service.DisciplinaService;
import com.br.sga.service.dto.DisciplinaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {
    private final DisciplinaService service;

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> buscarTodos() {
        List<DisciplinaDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> buscarPorId(@PathVariable("id") Long id) {
        DisciplinaDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> salvar(@Valid @RequestBody DisciplinaDTO dto) {
        DisciplinaDTO disciplinaDTO = service.salvar(dto);
        return new ResponseEntity<>(disciplinaDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DisciplinaDTO> atualizar(@Valid @RequestBody DisciplinaDTO dto) {
        DisciplinaDTO disciplinaDTO = service.salvar(dto);
        return new ResponseEntity<>(disciplinaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleter(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

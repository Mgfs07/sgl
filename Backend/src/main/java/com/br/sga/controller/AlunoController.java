package com.br.sga.controller;

import com.br.sga.service.AlunoService;
import com.br.sga.service.dto.AlunoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> buscarTodos() {
        List<AlunoDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable("id") Long id) {
        AlunoDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> salvar(@Valid @RequestBody AlunoDTO dto) {
        AlunoDTO alunoDTO = service.salvar(dto);
        return new ResponseEntity<>(alunoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AlunoDTO> atualizar(@Valid @RequestBody AlunoDTO dto) {
        AlunoDTO alunoDTO = service.salvar(dto);
        return new ResponseEntity<>(alunoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleter(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

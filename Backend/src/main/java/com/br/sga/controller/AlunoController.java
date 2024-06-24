package com.br.sga.controller;

import com.br.sga.service.AlunoService;
import com.br.sga.service.dto.AlunoDTO;
import com.br.sga.service.dto.UsuarioDTO;
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

    @GetMapping("/{matricula}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("matricula") String matricula) {
        UsuarioDTO listagem = service.buscar(matricula);
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

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleter(@PathVariable("matricula") String matricula) {
        service.deletar(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

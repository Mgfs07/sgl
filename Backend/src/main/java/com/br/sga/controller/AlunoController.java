package com.br.sga.controller;

import com.br.sga.service.AlunoService;
import com.br.sga.service.dto.AlunoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable("id") Integer id) {
        AlunoDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> salvar(@RequestBody AlunoDTO dto){
        AlunoDTO pagamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(pagamentoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AlunoDTO> atualizar(@RequestBody AlunoDTO dto){
        AlunoDTO pagamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(pagamentoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

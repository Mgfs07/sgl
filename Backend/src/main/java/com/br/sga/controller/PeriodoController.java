package com.br.sga.controller;

import com.br.sga.service.PeriodoService;
import com.br.sga.service.dto.PeriodoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodos")
@RequiredArgsConstructor
public class PeriodoController {

    private final PeriodoService service;

    @GetMapping
    public ResponseEntity<List<PeriodoDTO>> buscarTodos() {
        List<PeriodoDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoDTO> buscarPorId(@PathVariable("id") Long id) {
        PeriodoDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PeriodoDTO> salvar(@RequestBody PeriodoDTO dto){
        PeriodoDTO periodoDTO = service.salvar(dto);
        return new ResponseEntity<>(periodoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PeriodoDTO> atualizar(@RequestBody PeriodoDTO dto){
        PeriodoDTO periodoDTO = service.salvar(dto);
        return new ResponseEntity<>(periodoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/vigente")
    public ResponseEntity<Long> periodoVigente() {
        Long listagem = service.periodoVigente();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }
}

package com.br.sga.controller;

import com.br.sga.service.CoordenadorService;
import com.br.sga.service.dto.CoordenadorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordenadores")
@RequiredArgsConstructor
public class CoordenadorController {

    private final CoordenadorService service;

    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> buscarTodos() {
        List<CoordenadorDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDTO> buscarPorId(@PathVariable("id") Long id) {
        CoordenadorDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoordenadorDTO> salvar(@RequestBody CoordenadorDTO dto){
        CoordenadorDTO coordenadoriaDTO = service.salvar(dto);
        return new ResponseEntity<>(coordenadoriaDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CoordenadorDTO> atualizar(@RequestBody CoordenadorDTO dto){
        CoordenadorDTO coordenadoriaDTO = service.salvar(dto);
        return new ResponseEntity<>(coordenadoriaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

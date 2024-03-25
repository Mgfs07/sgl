package com.br.sga.controller;

import com.br.sga.service.CoordenadoriaService;
import com.br.sga.service.dto.CoordenadoriaDTO;
import com.br.sga.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordenadorias")
@RequiredArgsConstructor
public class CoordenadoriaController {

    private final CoordenadoriaService service;

    @GetMapping
    public ResponseEntity<List<CoordenadoriaDTO>> buscarTodos() {
        List<CoordenadoriaDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadoriaDTO> buscarPorId(@PathVariable("id") Long id) {
        CoordenadoriaDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoordenadoriaDTO> salvar(@RequestBody CoordenadoriaDTO dto){
        CoordenadoriaDTO coordenadoriaDTO = service.salvar(dto);
        return new ResponseEntity<>(coordenadoriaDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CoordenadoriaDTO> atualizar(@RequestBody CoordenadoriaDTO dto){
        CoordenadoriaDTO coordenadoriaDTO = service.salvar(dto);
        return new ResponseEntity<>(coordenadoriaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DropdownDTO>> buscarDropdown() {
        List<DropdownDTO> dropdownDTOS = service.buscarDropdown();
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }
}

package com.br.sga.controller;

import com.br.sga.service.EquipamentoService;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.EquipamentoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService service;

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> buscarTodos() {
        List<EquipamentoDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> buscarPorId(@PathVariable("id") Long id) {
        EquipamentoDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipamentoDTO> salvar(@Valid @RequestBody EquipamentoDTO dto){
        EquipamentoDTO equipamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(equipamentoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EquipamentoDTO> atualizar(@RequestBody EquipamentoDTO dto){
        EquipamentoDTO equipamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(equipamentoDTO, HttpStatus.CREATED);
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

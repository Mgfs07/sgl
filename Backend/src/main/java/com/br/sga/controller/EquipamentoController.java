package com.br.sga.controller;

import com.br.sga.service.EquipamentoService;
import com.br.sga.service.dto.EquipamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamento")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService service;

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> buscarTodos() {
        List<EquipamentoDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> buscarPorId(@PathVariable("id") Integer id) {
        EquipamentoDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipamentoDTO> salvar(@RequestBody EquipamentoDTO dto){
        EquipamentoDTO pagamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(pagamentoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EquipamentoDTO> atualizar(@RequestBody EquipamentoDTO dto){
        EquipamentoDTO pagamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(pagamentoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

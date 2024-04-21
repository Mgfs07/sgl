package com.br.sga.controller;

import com.br.sga.service.EventoService;
import com.br.sga.service.dto.EventoDTO;
import com.br.sga.service.dto.EventoListagemDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    @GetMapping
    public ResponseEntity<List<EventoListagemDTO>> buscarTodos() {
        List<EventoListagemDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable("id") Long id) {
        EventoDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> salvar(@Valid @RequestBody EventoDTO dto) {
        EventoDTO disciplinaDTO = service.salvar(dto);
        return new ResponseEntity<>(disciplinaDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EventoDTO> atualizar(@Valid @RequestBody EventoDTO dto) {
        EventoDTO disciplinaDTO = service.salvar(dto);
        return new ResponseEntity<>(disciplinaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleter(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.br.sga.controller;

import com.br.sga.service.LocalService;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.LocalDTO;
import com.br.sga.service.dto.LocalListagemProjection;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locais")
@RequiredArgsConstructor
public class LocalController {

    private final LocalService service;

    @GetMapping
    public ResponseEntity<List<LocalListagemProjection>> buscarTodos() {
        List<LocalListagemProjection> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> buscarPorId(@PathVariable("id") Long id) {
        LocalDTO listagem = service.buscar(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocalDTO> salvar(@Valid @RequestBody LocalDTO dto) {
        LocalDTO localDTO = service.salvar(dto);
        return new ResponseEntity<>(localDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LocalDTO> atualizar(@Valid @RequestBody LocalDTO dto) {
        LocalDTO localDTO = service.salvar(dto);
        return new ResponseEntity<>(localDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleter(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DropdownDTO>> buscarDropdown() {
        List<DropdownDTO> dropdownDTOS = service.buscarDropdown();
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }

    @GetMapping("/disponiveis/{idAula}")
    public ResponseEntity<List<DropdownDTO>> buscarLocaisDisponiveis(@PathVariable Long idAula) {
        List<DropdownDTO> dropdownDTOS = service.buscarLocaisDisponiveis(idAula);
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }
}

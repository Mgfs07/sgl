package com.br.sga.controller;

import com.br.sga.service.ProfessorService;
import com.br.sga.service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public ResponseEntity<List<ProfessorListagemDTO>> buscarTodos() {
        List<ProfessorListagemDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("matricula") String matricula) {
        UsuarioDTO listagem = service.buscar(matricula);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/buscar/{matricula}")
    public ResponseEntity<ProfessorDTO> buscarProfessor(@PathVariable("matricula") String matricula) {
        ProfessorDTO listagem = service.buscarProfessor(matricula);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> salvar(@Valid @RequestBody ProfessorDTO dto) {
        ProfessorDTO ProfessorDTO = service.salvar(dto);
        return new ResponseEntity<>(ProfessorDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProfessorDTO> atualizar(@Valid @RequestBody ProfessorDTO dto) {
        ProfessorDTO ProfessorDTO = service.atualizar(dto);
        return new ResponseEntity<>(ProfessorDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleter(@PathVariable("matricula") String matricula) {
        service.deletar(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DropdownProfessorDTO>> buscarDropdown() {
        List<DropdownProfessorDTO> dropdownDTOS = service.buscarDropdown();
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }

    @GetMapping("/dropdown-prof-cord")
    public ResponseEntity<List<DropdownStringDTO>> buscarDropdownProfessorCoordenador() {
        List<DropdownStringDTO> dropdown = service.buscarDropdownProfessoresCoordenadores();
        return new ResponseEntity<>(dropdown, HttpStatus.OK);
    }
}

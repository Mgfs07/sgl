package com.br.sga.controller;

import com.br.sga.service.AulaService;
import com.br.sga.service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
public class AulaController {
    private final AulaService service;

    @GetMapping("/horarios-aluno/{matricula}")
    public ResponseEntity<List<HorariosAula>> buscarHorariosAulaAluno(@PathVariable String matricula) {
        List<HorariosAula> listagem = service.buscarHorariosAulaAluno(matricula);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DropdownDTO>> buscarTodos() {
        List<DropdownDTO> listagem = service.buscarDropdown();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping("/alocar-local")
    public ResponseEntity<Void> salvarLocalAula(@RequestBody AlocacaoLocalAulaDTO alocacaoLocalAulaDTO){
        service.salvarLocalAula(alocacaoLocalAulaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/alocacao")
    public ResponseEntity<List<AulaListDTO>> buscarAulasParaAlocacao(@RequestParam(required = false) Long idDisciplina){
        List<AulaListDTO> aulaListDTO = service.buscarAulasParaAlocacao(idDisciplina);
        return new ResponseEntity<>(aulaListDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> buscarAulaPorId(@PathVariable Long id){
        AulaDTO aulaDTO2 = service.buscarAulaPorId(id);
        return new ResponseEntity<>(aulaDTO2, HttpStatus.OK);
    }
}

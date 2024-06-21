package com.br.sga.controller;

import com.br.sga.service.AulaService;
import com.br.sga.service.dto.AulaDTO2;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.HorariosAula;
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

    @PostMapping("/alocar/{idLocal}/{idAula}")
    public ResponseEntity<Void> salvarLocalAula(@PathVariable Long idLocal, @PathVariable Long idAula){
        service.salvarLocalAula(idLocal, idAula);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/{idAula}")
//    public ResponseEntity<AulaDTO2> buscarAulaParaAlocacao(@PathVariable Long idAula){
//        AulaDTO2 aulaDTO2 = service.buscarAulaParaAlocacao(idAula);
//        return new ResponseEntity<>(aulaDTO2, HttpStatus.OK);
//    }
}

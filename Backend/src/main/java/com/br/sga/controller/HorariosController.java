package com.br.sga.controller;

import com.br.sga.service.HorariosService;
import com.br.sga.service.dto.HorariosAula2;
import com.br.sga.service.dto.Teste;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorariosController {

    private final HorariosService service;

//    @GetMapping
//    public ResponseEntity<List<AlunoDTO>> buscarTodos() {
//        List<AlunoDTO> listagem = service.buscarTodos();
//        return new ResponseEntity<>(listagem, HttpStatus.OK);
//    }

    @GetMapping("/{matricula}")
    public ResponseEntity<List<HorariosAula2>> buscarTodosAulas(@PathVariable String matricula) {
        List<HorariosAula2> listagem = service.buscarHorariosAulaAluno2(matricula);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Teste>> buscarHorariosAula() {
        List<Teste> listagem = service.buscarHorariosAula();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

}

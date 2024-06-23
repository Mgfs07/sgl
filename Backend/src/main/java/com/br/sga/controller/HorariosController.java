package com.br.sga.controller;

import com.br.sga.service.HorariosService;
import com.br.sga.service.dto.DropdownDTO;
import com.br.sga.service.dto.HorarioDTO;
import com.br.sga.service.dto.HorarioFiltroDTO;
import com.br.sga.service.dto.HorariosAula2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<HorarioDTO>> buscarHorariosAula() {
        List<HorarioDTO> listagem = service.buscarHorariosAula();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/dropdown/turma")
    public ResponseEntity<List<DropdownDTO>> buscarDropdown() {
        List<DropdownDTO> dropdownDTOS = service.buscarDropdownTurma();
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }

    @GetMapping("/horario")
    public ResponseEntity<List<HorarioDTO>> buscarHorario(@RequestBody HorarioFiltroDTO horarioFiltroDTO) {
        List<HorarioDTO> listagem = service.buscarHorarios(horarioFiltroDTO);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }


}

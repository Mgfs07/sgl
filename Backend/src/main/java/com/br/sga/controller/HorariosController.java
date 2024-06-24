package com.br.sga.controller;

import com.br.sga.domain.enums.TipoAtorBuscaEnum;
import com.br.sga.service.HorariosService;
import com.br.sga.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios/public")
@RequiredArgsConstructor
public class HorariosController {

    private final HorariosService service;

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
    public ResponseEntity<List<HorarioDTO>> buscarHorario(
            @RequestParam TipoAtorBuscaEnum tipoAtorBusca,
            @RequestParam(required = false) String rfId,
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) Long idTurma) {
        HorarioFiltroDTO horarioFiltroDTO = new HorarioFiltroDTO(tipoAtorBusca, rfId, matricula, idTurma);
        List<HorarioDTO> listagem = service.buscarHorarios(horarioFiltroDTO);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/dropdown/professor")
    public ResponseEntity<List<DropdownProfessorDTO>> buscarDropdownProfessor() {
        List<DropdownProfessorDTO> dropdownDTOS = service.buscarDropdownProfessor();
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }

}

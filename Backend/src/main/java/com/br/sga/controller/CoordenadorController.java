package com.br.sga.controller;

import com.br.sga.service.CoordenadorService;
import com.br.sga.service.dto.CoordenadorDTO;
import com.br.sga.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordenadores")
@RequiredArgsConstructor
public class CoordenadorController {

    private final CoordenadorService service;

    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> buscarTodos() {
        List<CoordenadorDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("matricula") String matricula) {
        UsuarioDTO listagem = service.buscar(matricula);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoordenadorDTO> salvar(@RequestBody CoordenadorDTO dto){
        CoordenadorDTO coordenadoriaDTO = service.salvar(dto);
        return new ResponseEntity<>(coordenadoriaDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CoordenadorDTO> atualizar(@RequestBody CoordenadorDTO dto){
        CoordenadorDTO coordenadoriaDTO = service.salvar(dto);
        return new ResponseEntity<>(coordenadoriaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable("matricula") String matricula ){
        service.deletar(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

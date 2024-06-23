package com.br.sga.controller;

import com.br.sga.service.AuditoriaService;
import com.br.sga.service.dto.AuditoriaEventoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService service;

    @GetMapping("/evento")
    public ResponseEntity<List<AuditoriaEventoDTO>> buscarTodosAuditoriasEvento() {
        List<AuditoriaEventoDTO> listagem = service.buscarTodasAuditoriasEvento();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaEventoDTO> buscarPorId(@PathVariable("id") Long id) {
        AuditoriaEventoDTO auditoria = service.buscar(id);
        return new ResponseEntity<>(auditoria, HttpStatus.OK);
    }
}

package com.br.sga.controller;

import com.br.sga.service.LocalEquipamentoService;
import com.br.sga.service.dto.LocalEquipamentoDTO;
import com.br.sga.service.dto.LocalEquipamentoListagemDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/local-equipamento")
@RequiredArgsConstructor
public class LocalEquipamentoController {

    private final LocalEquipamentoService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<LocalEquipamentoListagemDTO>> buscarEquipamentosLocalPorId(@PathVariable("id") Long id) {
        List<LocalEquipamentoListagemDTO> listagem = service.buscarEquipamentosLocalPorId(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody LocalEquipamentoDTO dto) {
        service.salvar(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{idLocal}/{idEquipamento}")
    public ResponseEntity<Void> deletarRelacaoLocalEquipamento(@PathVariable("idLocal") Long idLocal,
                                                               @PathVariable("idEquipamento") Long idEquipamento) {
        service.deletarLocalEquipamento(idLocal, idEquipamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

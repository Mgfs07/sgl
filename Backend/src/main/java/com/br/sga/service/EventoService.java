package com.br.sga.service;

import com.br.sga.domain.Evento;
import com.br.sga.repository.EventoRepository;
import com.br.sga.service.dto.EventoDTO;
import com.br.sga.service.dto.EventoListagemDTO;
import com.br.sga.service.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventoService {
    private final EventoRepository repository;
    private final EventoMapper mapper;

    public List<EventoListagemDTO> buscarTodos() {
        return repository.buscarTodos();
    }

    public EventoDTO buscar(Long id) {
        return mapper.toDto(buscarPorid(id));
    }

    private Evento buscarPorid(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }

    public EventoDTO salvar(EventoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

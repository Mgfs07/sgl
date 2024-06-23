package com.br.sga.service;

import com.br.sga.domain.Evento;
import com.br.sga.repository.EventoRepository;
import com.br.sga.repository.UsuarioRepository;
import com.br.sga.service.dto.EventoDTO;
import com.br.sga.service.dto.EventoListagemDTO;
import com.br.sga.service.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    private final UsuarioRepository usuarioRepository;
    private final EventoMapper mapper;
    private final AuditoriaService auditoriaService;

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
        if (dto.getId() == null) {
            EventoDTO novoEvento = mapper.toDto(repository.save(mapper.toEntity(dto)));
            novoEvento.setMatriculaUsuarioLogado(dto.getMatriculaUsuarioLogado());
            auditoriaService.auditarCriarEvento(novoEvento);
            return novoEvento;
        }
        if (dto.getId() != null) {
            Evento eventoOriginal = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Evento não encontrado com ID: " + dto.getId()));

            Evento eventoOriginalCopia = new Evento();
            BeanUtils.copyProperties(eventoOriginal, eventoOriginalCopia);

            EventoDTO eventoEditado = mapper.toDto(repository.save(mapper.toEntity(dto)));
            eventoEditado.setMatriculaUsuarioLogado(dto.getMatriculaUsuarioLogado());

            auditoriaService.auditarEditarEvento(eventoEditado, eventoOriginalCopia);

            return eventoEditado;
        }
        return dto;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

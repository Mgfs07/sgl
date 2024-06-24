package com.br.sga.service;

import com.br.sga.domain.AuditoriaEvento;
import com.br.sga.domain.Evento;
import com.br.sga.repository.AuditoriaAlocacaoRepository;
import com.br.sga.repository.AuditoriaEventoRepository;
import com.br.sga.repository.LocalRepository;
import com.br.sga.repository.UsuarioRepository;
import com.br.sga.service.dto.AuditoriaEventoDTO;
import com.br.sga.service.dto.EventoDTO;
import com.br.sga.service.mapper.AuditoriaEventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuditoriaService {

    private final AuditoriaEventoRepository auditoriaEventoRepository;
    private final AuditoriaEventoMapper mapper;
    private final AuditoriaAlocacaoRepository auditoriaAlocacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalRepository localRepository;

    public AuditoriaEventoDTO auditarCriarEvento(EventoDTO eventoDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        eventoDTO.setMatriculaUsuarioLogado(authentication.getName());
        AuditoriaEventoDTO auditoriaEvento = preencherAuditoriaNovoEvento(eventoDTO);
        return mapper.toDto(auditoriaEventoRepository.save(mapper.toEntity(auditoriaEvento)));
    }

    public AuditoriaEventoDTO preencherAuditoriaNovoEvento(EventoDTO eventoDTO) {
        AuditoriaEventoDTO auditoriaEventoDTO = new AuditoriaEventoDTO();
        auditoriaEventoDTO.setMatriculaUsuarioLogado(eventoDTO.getMatriculaUsuarioLogado());
        auditoriaEventoDTO.setMatriculaSolicitante(eventoDTO.getMatriculaSolicitante());
        auditoriaEventoDTO.setNomeUsuarioAlteracao(usuarioRepository.findByMatricula(eventoDTO.getMatriculaUsuarioLogado()).getNome());
        auditoriaEventoDTO.setIdEvento(eventoDTO.getId());
        auditoriaEventoDTO.setData(LocalDate.now());
        auditoriaEventoDTO.setAcao(preencherStringAcaoCriar(eventoDTO));
        return auditoriaEventoDTO;
    }

    public String preencherStringAcaoCriar(EventoDTO eventoDTO) {
        return "O evento " + eventoDTO.getDescricao() + " foi criado em " + eventoDTO.getData();
    }

    public AuditoriaEventoDTO auditarEditarEvento(EventoDTO novoEvento, Evento eventoOriginal) {
        AuditoriaEventoDTO auditoriaEvento = preencherAuditoriaEdicaoEvento(novoEvento, eventoOriginal);
        return mapper.toDto(auditoriaEventoRepository.save(mapper.toEntity(auditoriaEvento)));
    }

    public AuditoriaEventoDTO preencherAuditoriaEdicaoEvento(EventoDTO novoEvento, Evento eventoOriginal) {
        String nomeUsuarioAlteracao = usuarioRepository.findByMatricula(novoEvento.getMatriculaUsuarioLogado()).getNome();
        String nomeUsuarioSolicitante = usuarioRepository.findByMatricula(novoEvento.getMatriculaSolicitante()).getNome();
        AuditoriaEventoDTO auditoriaEventoDTO = new AuditoriaEventoDTO();
        auditoriaEventoDTO.setMatriculaUsuarioLogado(novoEvento.getMatriculaUsuarioLogado());
        auditoriaEventoDTO.setMatriculaSolicitante(novoEvento.getMatriculaSolicitante());
        auditoriaEventoDTO.setNomeUsuarioAlteracao(nomeUsuarioAlteracao);
        auditoriaEventoDTO.setIdEvento(novoEvento.getId());
        auditoriaEventoDTO.setData(LocalDate.now());
        auditoriaEventoDTO.setAcao(preencherStringAcaoEdicao(novoEvento, eventoOriginal, nomeUsuarioAlteracao, nomeUsuarioSolicitante));

        return auditoriaEventoDTO;
    }

    private String preencherStringAcaoEdicao(EventoDTO eventoDTO, Evento eventoOriginal, String nomeUsuarioAlteracao, String nomeUsuarioSolicitante) {
        StringBuilder acao = new StringBuilder("Edição do evento - " + eventoOriginal.getDescricao() + "\n\n\n");

        if (!eventoOriginal.getDescricao().equals(eventoDTO.getDescricao())) {
            acao.append("Descrição alterada de '" + eventoOriginal.getDescricao() + "' para '" + eventoDTO.getDescricao() + "'. \n\n\n");
        }
        if (!eventoOriginal.getHoraInicio().equals(eventoDTO.getHoraInicio())) {
            acao.append("Hora de início alterada de '" + eventoOriginal.getHoraInicio() + "' para '" + eventoDTO.getHoraInicio() + "'. \n\n\n");
        }
        if (!eventoOriginal.getHoraFim().equals(eventoDTO.getHoraFim())) {
            acao.append("Hora de fim alterada de '" + eventoOriginal.getHoraFim() + "' para '" + eventoDTO.getHoraFim() + "'. \n\n\n");
        }
        if (!eventoOriginal.getLocal().getId().equals(eventoDTO.getIdLocal())) {
            acao.append("Local alterado de '" + eventoOriginal.getLocal().getNome() + "' para '" + localRepository.findNomeById(eventoDTO.getIdLocal()) + "'. \n\n\n");
        }
        if (!eventoOriginal.getData().equals(eventoDTO.getData())) {
            acao.append("Data alterada de '" + eventoOriginal.getData() + "' para '" + eventoDTO.getData() + "'. \n\n\n");
        }
        if (!eventoOriginal.getUsuarioSolicitante().getNome().equals(nomeUsuarioSolicitante)) {
            acao.append("Usuário solicitante alterado de '" + eventoOriginal.getUsuarioSolicitante().getNome() + "' para '" + nomeUsuarioSolicitante + "'. ");
        }
        return acao.toString();
    }

    public List<AuditoriaEventoDTO> buscarTodasAuditoriasEvento() {
        return auditoriaEventoRepository.buscarTodasAuditoriasListDTO();
    }

    public AuditoriaEventoDTO buscar(Long id) {
        AuditoriaEvento auditoriaEvento = buscarPorid(id);
        AuditoriaEventoDTO auditoriaEventoDTO = mapper.toDto(auditoriaEvento);
        auditoriaEventoDTO.setNomeUsuarioAlteracao(usuarioRepository.findByMatricula(auditoriaEvento.getUsuarioLogado().getMatricula()).getNome());
        return auditoriaEventoDTO;
    }

    private AuditoriaEvento buscarPorid(Long id) {
        return auditoriaEventoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Auditoria não encontrada"));
    }
}

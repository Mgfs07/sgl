package com.br.sga.service;

import com.br.sga.repository.LocalEquipamentoRepository;
import com.br.sga.service.dto.LocalEquipamentoDTO;
import com.br.sga.service.dto.LocalEquipamentoListagemDTO;
import com.br.sga.service.mapper.LocalEquipamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalEquipamentoService {
    
    private final LocalEquipamentoRepository repository;
    private final LocalEquipamentoMapper mapper;

    public List<LocalEquipamentoListagemDTO> buscarEquipamentosLocalPorId(Long idLocal) {
        return repository.buscarEquipamentosLocalPorId(idLocal);
    }

    public void salvar(LocalEquipamentoDTO dto) {
        var localEquipamento = mapper.toEntity(dto);
        repository.save(localEquipamento);
    }

    public void deletarLocalEquipamento(Long idLocal, Long idEquipamento) {
        repository.deleteLocalEquipamentoById_IdLocalAndId_IdEquipamento(idLocal, idEquipamento);
    }
}

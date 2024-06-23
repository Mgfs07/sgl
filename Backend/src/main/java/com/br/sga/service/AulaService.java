package com.br.sga.service;

import com.br.sga.repository.AulaRepository;
import com.br.sga.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AulaService {

    private final AulaRepository repository;


    public List<HorariosAula> buscarHorariosAulaAluno(String matricula) {
        return repository.buscarHorariosAulaAluno(matricula);
    }

    public List<HorariosAula2> buscarHorariosAulaAluno2(String matricula) {
        return repository.buscarHorariosAulaAluno2(matricula);
    }

    public List<Teste> buscarHorarios() {
        var teste = repository.buscarHorarios("2020122760323", 1L);
        teste.forEach(item -> item.setAulas(repository.buscarHorariosAulaAluno2("2020122760323")));
        return teste;
    }

    public List<DropdownDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }

    public void salvarLocalAula(Long idLocal, Long idAula) {
        repository.salvarLocalAula(idLocal, idAula);
    }

//    public AulaDTO2 buscarAulaParaAlocacao(Long idAula) {
//        return repository.buscarAulaParaAlocacao(idAula);
//    }


}

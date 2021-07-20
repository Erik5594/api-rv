package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.DetalheErro;
import com.erik5594.apivr.domain.Voto;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.repository.VotoRepository;
import com.erik5594.apivr.service.AssociadoService;
import com.erik5594.apivr.service.PautaService;
import com.erik5594.apivr.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:24
 */

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository repository;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private PautaService pautaService;

    @Override
    public void salvar(Voto voto) {
        validar(voto);
        voto.setId(gerarId());
        voto.setPauta(pautaService.buscar(voto.getPauta().getId()));
        repository.save(voto);
    }

    private String gerarId(){
        return UUID.randomUUID().toString();
    }

    private void validar(Voto voto){
        if(voto.getVoto() == null){
            throw new ValidacaoException("O voto deve ser SIM ou NAO.", 400);
        }
        associadoService.validar(voto.getAssociado());
        pautaService.validar(voto.getPauta());
        Voto votoR= repository.findVotoByAssociado_CpfAndPauta_Id(voto.getAssociado().getCpf(), voto.getPauta().getId())
                .orElse(null);
        if(votoR != null){
            throw new ValidacaoException("Associado já registrou um voto anteriormente para essa pauta.", 400);
        }
    }
}

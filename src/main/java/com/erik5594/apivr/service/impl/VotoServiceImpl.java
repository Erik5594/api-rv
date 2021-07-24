package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.ResultadoVotacao;
import com.erik5594.apivr.domain.Voto;
import com.erik5594.apivr.domain.VotoEnum;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.repository.VotoRepository;
import com.erik5594.apivr.service.AssociadoService;
import com.erik5594.apivr.service.PautaService;
import com.erik5594.apivr.service.VotoService;
import com.erik5594.apivr.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        voto.setId(Util.gerarId());
        voto.setPauta(pautaService.buscar(voto.getPauta().getId()));
        repository.save(voto);
    }

    private void validar(Voto voto){
        if(voto.getVoto() == null){
            throw new ValidacaoException("O voto deve ser SIM ou NAO.", 400);
        }
        Voto votoR = repository.findVotoByAssociado_CpfAndPauta_Id(voto.getAssociado().getCpf(), voto.getPauta().getId())
                .orElse(null);
        associadoService.validar(voto.getAssociado());
        pautaService.validar(voto.getPauta());
        if(votoR != null){
            throw new ValidacaoException("Associado já registrou um voto anteriormente para essa pauta.", 400);
        }
    }

    @Override
    public ResultadoVotacao resultado(String idPauta) {
        boolean votacaoAberta = pautaService.votacaoAberta(idPauta);
        List<Voto> votos = repository.findAllByPauta_Id(idPauta).orElse(new ArrayList<>());
        long qtdeVotosSim = votos.stream().filter(voto -> voto.getVoto() == VotoEnum.SIM).count();
        long qtdeVotosNao = votos.stream().filter(voto -> voto.getVoto() == VotoEnum.NAO).count();
        return new ResultadoVotacao(qtdeVotosSim, qtdeVotosNao, votacaoAberta);
    }

    public void setRepository(VotoRepository repository) {
        this.repository = repository;
    }

    public void setAssociadoService(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    public void setPautaService(PautaService pautaService) {
        this.pautaService = pautaService;
    }
}

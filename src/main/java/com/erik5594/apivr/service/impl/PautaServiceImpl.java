package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.repository.PautaRepository;
import com.erik5594.apivr.service.PautaService;
import com.erik5594.apivr.util.DataUtils;
import com.erik5594.apivr.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:23
 */
@Service
public class PautaServiceImpl implements PautaService {

    private static final int TEMPO_DEFAULT = 60;

    @Autowired
    private PautaRepository repository;


    @Override
    public Pauta salvar(Pauta pauta) {
        pauta.setId(Util.gerarId());
        return repository.save(pauta);
    }

    @Override
    public Pauta buscar(String id) {
        Pauta pauta = repository.findById(id).orElse(null);
        if(pauta == null)
            throw new ValidacaoException("Pauta não encontrada.",404);
        return pauta;
    }

    public boolean votacaoAberta(String idPauta){
        return buscar(idPauta).isVotacaoAberta();
    }

    @Override
    public List<Pauta> buscarTodas() {
        return repository.findAll();
    }

    private Pauta buscarPautaValidaAbrirSessao(String id){
        Pauta pauta = repository.findById(id).orElse(null);
        if(pauta == null){
            throw new ValidacaoException("Pauta não encontrada", 404);
        }
        if(pauta.getInicioSessao() != null || pauta.getFimSessao() != null){
            throw new ValidacaoException("Não é possível abrir sessão para a pauta, pois a mesma já foi aberta uma vez", 400);
        }
        return pauta;
    }

    @Override
    public void abrirSessao(String idPauta, int segundos) {
        Pauta pauta = buscarPautaValidaAbrirSessao(idPauta);
        pauta.setInicioSessao(new Date());
        pauta.setFimSessao(DataUtils.adcionarSegundos(getSegundos(segundos)));
        repository.save(pauta);
    }

    private int getSegundos(int segundos) {
        return segundos <= 0 ? TEMPO_DEFAULT : segundos;
    }

    @Override
    public void validar(Pauta pauta) {
        if(pauta == null || StringUtils.isBlank(pauta.getId()))
            throw new ValidacaoException("Pauta deve ser informada.", 400);

        Pauta pautaR = buscar(pauta.getId());

        if(!pautaR.isVotacaoAberta())
            throw new ValidacaoException("Sessão para votação não está aberta.", 400);
    }
}
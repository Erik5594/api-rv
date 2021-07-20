package com.erik5594.apivr.service;

import com.erik5594.apivr.domain.DetalheErro;
import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.repository.PautaRepository;
import com.erik5594.apivr.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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
        pauta.setId(gerarId());
        return repository.save(pauta);
    }

    private String gerarId() {
        return UUID.randomUUID().toString();
    }

    private Pauta buscarPautaValidaAbrirSessao(String id){
        Pauta pauta = repository.findById(id).orElse(null);
        if(pauta == null){
            throw new ValidacaoException(new DetalheErro("Pauta não encontrada", 404));
        }
        if(pauta.getInicioSessao() != null || pauta.getFimSessao() != null){
            throw new ValidacaoException(new DetalheErro("Não é possível abrir sessão para a pauta, pois a mesma já foi aberta uma vez", 400));
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

}
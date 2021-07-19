package com.erik5594.apivr.service;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:23
 */
@Service
public class PautaServiceImpl implements PautaService{

    @Autowired
    private PautaRepository repository;


    @Override
    public Pauta salvar(Pauta pauta) {
        pauta.setId(gerarId());
        return repository.save(pauta);
    }

    private String gerarId(){
        return UUID.randomUUID().toString();
    }
}

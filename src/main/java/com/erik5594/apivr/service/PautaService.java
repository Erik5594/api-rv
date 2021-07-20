package com.erik5594.apivr.service;

import com.erik5594.apivr.domain.Pauta;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:22
 */
public interface PautaService {

    Pauta salvar(Pauta pauta);
    Pauta buscar(String id);
    void abrirSessao(String idPauta, int segundos);
    void validar(Pauta pauta);

}

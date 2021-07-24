package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.repository.PautaRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 24/07/2021 - 18:40
 */
public class PautaServiceAbrirSessaoTest {

    private PautaServiceImpl pautaService;
    private PautaRepository pautaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before(){
        pautaService = new PautaServiceImpl();
        pautaRepository = Mockito.mock(PautaRepository.class);
        pautaService.setRepository(pautaRepository);
    }

    @Test
    public void pautaNaoEncontrada(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");

        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.empty());

        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Pauta não encontrada"));
        //acao
        pautaService.abrirSessao(pauta.getId(), 7200);
        //validacao
    }

    @Test
    public void pautaJaIniciada(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");
        pauta.setInicioSessao(new Date());

        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.of(pauta));

        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Não é possível abrir sessão para a pauta, pois a mesma já foi aberta uma vez"));
        //acao
        pautaService.abrirSessao(pauta.getId(), 7200);
        //validacao
    }

    @Test
    public void pautaAindaNaoIniciada(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");

        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.of(pauta));

        //acao
        pautaService.abrirSessao(pauta.getId(), 7200);
        //validacao
    }

    /*
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
     */
}

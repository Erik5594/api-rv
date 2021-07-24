package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.repository.PautaRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 24/07/2021 - 18:40
 */
public class PautaServiceBuscarTest {

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
    public void pautaInexistente(){
        //cenario
        String idPauta = "abc";
        Mockito.when(pautaRepository.findById(idPauta)).thenReturn(Optional.empty());

        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Pauta não encontrada."));
        //acao
        pautaService.buscar(idPauta);
    }

    @Test
    public void pautaExiste(){
        //cenario
        String idPauta = "abc";
        Mockito.when(pautaRepository.findById(idPauta)).thenReturn(Optional.of(new Pauta()));

        //acao
        pautaService.buscar(idPauta);
    }

    @Test
    public void pautaEncontradaComVotacaoAberta(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");
        pauta.setInicioSessao(adcionarHorasNaData(-2));
        pauta.setFimSessao(adcionarHorasNaData(1));
        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.of(pauta));

        //acao
        boolean aberta = pautaService.votacaoAberta(pauta.getId());

        //validacao
        Assert.assertTrue(aberta);
    }

    @Test
    public void pautaEncontradaComVotacaoFechada(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");
        pauta.setInicioSessao(adcionarHorasNaData(-3));
        pauta.setFimSessao(adcionarHorasNaData(-1));
        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.of(pauta));

        //acao
        boolean aberta = pautaService.votacaoAberta(pauta.getId());

        //validacao
        Assert.assertFalse(aberta);
    }

    private Date adcionarHorasNaData(int horas){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, horas);
        return calendar.getTime();
    }
}

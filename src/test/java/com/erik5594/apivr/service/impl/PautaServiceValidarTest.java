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

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 24/07/2021 - 18:40
 */
public class PautaServiceValidarTest {

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
    public void pautaNaoInformada(){
        //cenario
        Pauta pauta = null;
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Pauta deve ser informada."));
        //acao
        pautaService.validar(pauta);
    }

    @Test
    public void pautaSemId(){
        //cenario
        Pauta pauta = new Pauta();
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Pauta deve ser informada."));
        //acao
        pautaService.validar(pauta);
    }

    @Test
    public void pautaNaoEncontrada(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");

        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.empty());

        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Pauta não encontrada."));
        //acao
        pautaService.validar(pauta);
    }

    @Test
    public void pautaComVotacaoAberta(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");
        pauta.setInicioSessao(adcionarHorasNaData(-1));
        pauta.setFimSessao(adcionarHorasNaData(1));

        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.of(pauta));

        //acao
        pautaService.validar(pauta);
    }

    @Test
    public void pautaComVotacaoFechada(){
        //cenario
        Pauta pauta = new Pauta();
        pauta.setId("abc");
        pauta.setInicioSessao(adcionarHorasNaData(-3));
        pauta.setFimSessao(adcionarHorasNaData(-1));

        Mockito.when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.of(pauta));

        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Sessão para votação não está aberta."));
        //acao
        pautaService.validar(pauta);
    }

    private Date adcionarHorasNaData(int horas){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, horas);
        return calendar.getTime();
    }
}

package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.Associado;
import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.domain.Voto;
import com.erik5594.apivr.domain.VotoEnum;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.repository.VotoRepository;
import com.erik5594.apivr.service.AssociadoService;
import com.erik5594.apivr.service.PautaService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 24/07/2021 - 16:51
 */
public class VotoServiceSalvaImplTest {

    private VotoServiceImpl votoService;
    private VotoRepository votoRepository;
    private AssociadoService associadoService;
    private PautaService pautaService;
    private Voto voto;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before(){
        votoService = new VotoServiceImpl();

        votoRepository = Mockito.mock(VotoRepository.class);
        votoService.setRepository(votoRepository);

        associadoService = Mockito.mock(AssociadoService.class);
        votoService.setAssociadoService(associadoService);

        pautaService = Mockito.mock(PautaService.class);
        votoService.setPautaService(pautaService);

        voto = getVoto();
    }

    @Test
    public void votoNullNaoPodeSalvar(){
        //cenario
        voto.setVoto(null);
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("O voto deve ser SIM ou NAO."));
        //acao
        votoService.salvar(voto);
        //validacao
    }

    @Test
    public void associadoJaRegistrouVoto(){
        //cenario
        Mockito.when(votoRepository.findVotoByAssociado_CpfAndPauta_Id(voto.getAssociado().getCpf(), voto.getPauta().getId()))
                .thenReturn(Optional.of(new Voto()));
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Associado já registrou um voto anteriormente para essa pauta."));
        //acao
        votoService.salvar(voto);
        //validacao
    }

    private Voto getVoto(){
        Voto voto = new Voto();
        voto.setVoto(VotoEnum.SIM);
        Pauta pauta = new Pauta();
        pauta.setId("abc");
        voto.setPauta(pauta);
        Associado associado = new Associado();
        associado.setCpf("12345678910");
        voto.setAssociado(associado);
        return voto;
    }
}

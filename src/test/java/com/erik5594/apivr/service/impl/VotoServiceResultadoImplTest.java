package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.Associado;
import com.erik5594.apivr.domain.ResultadoVotacao;
import com.erik5594.apivr.domain.Voto;
import com.erik5594.apivr.domain.VotoEnum;
import com.erik5594.apivr.repository.VotoRepository;
import com.erik5594.apivr.service.PautaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 24/07/2021 - 17:18
 */
public class VotoServiceResultadoImplTest {

    private VotoServiceImpl votoService;
    private PautaService pautaService;
    private VotoRepository votoRepository;

    @Before
    public void before(){
        votoService = new VotoServiceImpl();

        pautaService = Mockito.mock(PautaService.class);
        votoService.setPautaService(pautaService);

        votoRepository = Mockito.mock(VotoRepository.class);
        votoService.setRepository(votoRepository);
    }

    @Test
    public void aprovadoVotacaoFechada(){
        //cenario
        String idPauta = "abd";
        Mockito.when(pautaService.votacaoAberta(idPauta)).thenReturn(false);
        Voto voto1 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto2 = new Voto(VotoEnum.NAO, idPauta, new Associado());
        Voto voto3 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto4 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto5 = new Voto(VotoEnum.SIM, idPauta, new Associado());

        List<Voto> votos = Arrays.asList(voto1, voto2, voto3, voto4, voto5);
        Mockito.when(votoRepository.findAllByPauta_Id(idPauta)).thenReturn(Optional.of(votos));
        //acao
        ResultadoVotacao resutlado = votoService.resultado(idPauta);

        //validacao
        Assert.assertEquals(4, resutlado.getQtdeVotosSim());
        Assert.assertEquals(1, resutlado.getQtdeVotosNao());
        Assert.assertEquals("Aprovado", resutlado.getResultado());
    }

    @Test
    public void aprovadoVotacaoAberta(){
        //cenario
        String idPauta = "abd";
        Mockito.when(pautaService.votacaoAberta(idPauta)).thenReturn(true);
        Voto voto1 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto2 = new Voto(VotoEnum.NAO, idPauta, new Associado());
        Voto voto3 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto4 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto5 = new Voto(VotoEnum.SIM, idPauta, new Associado());

        List<Voto> votos = Arrays.asList(voto1, voto2, voto3, voto4, voto5);
        Mockito.when(votoRepository.findAllByPauta_Id(idPauta)).thenReturn(Optional.of(votos));
        //acao
        ResultadoVotacao resutlado = votoService.resultado(idPauta);

        //validacao
        Assert.assertEquals(4, resutlado.getQtdeVotosSim());
        Assert.assertEquals(1, resutlado.getQtdeVotosNao());
        Assert.assertEquals("Parcialmente Aprovado", resutlado.getResultado());
    }

    @Test
    public void reprovadoVotacaoFechada(){
        //cenario
        String idPauta = "abd";
        Mockito.when(pautaService.votacaoAberta(idPauta)).thenReturn(false);
        Voto voto1 = new Voto(VotoEnum.NAO, idPauta, new Associado());
        Voto voto2 = new Voto(VotoEnum.NAO, idPauta, new Associado());
        Voto voto3 = new Voto(VotoEnum.NAO, idPauta, new Associado());
        Voto voto4 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto5 = new Voto(VotoEnum.SIM, idPauta, new Associado());

        List<Voto> votos = Arrays.asList(voto1, voto2, voto3, voto4, voto5);
        Mockito.when(votoRepository.findAllByPauta_Id(idPauta)).thenReturn(Optional.of(votos));
        //acao
        ResultadoVotacao resutlado = votoService.resultado(idPauta);

        //validacao
        Assert.assertEquals(2, resutlado.getQtdeVotosSim());
        Assert.assertEquals(3, resutlado.getQtdeVotosNao());
        Assert.assertEquals("Reprovado", resutlado.getResultado());
    }

    @Test
    public void reprovadoVotacaoAberta(){
        //cenario
        String idPauta = "abd";
        Mockito.when(pautaService.votacaoAberta(idPauta)).thenReturn(true);
        Voto voto1 = new Voto(VotoEnum.NAO, idPauta, new Associado());
        Voto voto2 = new Voto(VotoEnum.NAO, idPauta, new Associado());
        Voto voto3 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto4 = new Voto(VotoEnum.SIM, idPauta, new Associado());
        Voto voto5 = new Voto(VotoEnum.NAO, idPauta, new Associado());

        List<Voto> votos = Arrays.asList(voto1, voto2, voto3, voto4, voto5);
        Mockito.when(votoRepository.findAllByPauta_Id(idPauta)).thenReturn(Optional.of(votos));
        //acao
        ResultadoVotacao resutlado = votoService.resultado(idPauta);

        //validacao
        Assert.assertEquals(2, resutlado.getQtdeVotosSim());
        Assert.assertEquals(3, resutlado.getQtdeVotosNao());
        Assert.assertEquals("Parcialmente Reprovado", resutlado.getResultado());
    }
}

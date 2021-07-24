package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.Associado;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.service.ValidaCPF;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * @author erik_
 * Data Criacao: 24/07/2021 - 11:06
 */
public class AssociadoServiceImplTest {

    private AssociadoServiceImpl associadoService;
    private Associado associado;
    private ValidaCPF validaCPF;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before(){
        associadoService = new AssociadoServiceImpl();
        validaCPF = Mockito.mock(ValidaCPF.class);
        associadoService.setValidaCPF(validaCPF);
        associado = getAssociado();
    }

    @Test
    public void associadoNaoInformado(){
        //cenario
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Associado deve ser informado!"));
        //acao
        associadoService.validar(null);
    }

    @Test
    public void associadoCpfNaoInformado(){
        //cenario
        associado.setCpf(null);
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("CPF do associado deve ser informado!"));

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoCpfEmBranco(){
        //cenario
        associado.setCpf("   ");
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("CPF do associado deve ser informado!"));

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoCpfVazio(){
        //cenario
        associado.setCpf("");
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("CPF do associado deve ser informado!"));

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoNomeNaoInformado(){
        //cenario
        associado.setNome(null);
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Nome do associado deve ser informado!"));

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoNomeEmBranco(){
        //cenario
        associado.setNome("    ");
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Nome do associado deve ser informado!"));

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoNomeVazio(){
        //cenario
        associado.setNome("");
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Nome do associado deve ser informado!"));

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoCpfInvalido(){
        //cenario
        associado.setCpf("12345678910");

        Mockito.when(validaCPF.aptoVotar(associado.getCpf())).thenThrow(ValidacaoException.class);

        expectedException.expect(ValidacaoException.class);

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoCpfValidoEHabilitadoParaVotar(){
        //cenario

        Mockito.when(validaCPF.aptoVotar(associado.getCpf())).thenReturn(true);

        //acao
        associadoService.validar(associado);
    }

    @Test
    public void associadoCpfValidoENaoHabilitadoParaVotar(){
        //cenario
        Mockito.when(validaCPF.aptoVotar(associado.getCpf())).thenReturn(false);
        expectedException.expect(ValidacaoException.class);
        expectedException.expectMessage(Matchers.equalTo("Associado não está habilitado para realizar a votação!"));

        //acao
        associadoService.validar(associado);
    }

    private Associado getAssociado(){
        Associado associado = new Associado();
        associado.setCpf("03554424188");
        associado.setNome("Teste 1");
        return associado;
    }

}

package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.domain.Associado;
import com.erik5594.apivr.domain.DetalheErro;
import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.service.AssociadoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:39
 */
@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Override
    public void validar(Associado associado) {
        if(associado == null){
            throw new ValidacaoException("Associado deve ser informado!", 400);
        }
        if(StringUtils.isBlank(associado.getCpf())){
            throw new ValidacaoException("CPF do associado deve ser informado!", 400);
        }
        if(StringUtils.isBlank(associado.getNome())){
            throw new ValidacaoException("Nome do associado deve ser informado!", 400);
        }
    }
}

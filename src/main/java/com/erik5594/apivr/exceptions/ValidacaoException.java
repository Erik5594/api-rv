package com.erik5594.apivr.exceptions;

import com.erik5594.apivr.domain.DetalheErro;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 21:41
 */
public class ValidacaoException extends RuntimeException {

    private int status;

    public ValidacaoException(DetalheErro detalheErro) {
        super(detalheErro.getMensagem());
        this.status = detalheErro.getStatus();
    }

    public int getStatus() {
        return status;
    }
}

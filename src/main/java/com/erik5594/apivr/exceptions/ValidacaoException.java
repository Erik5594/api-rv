package com.erik5594.apivr.exceptions;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 21:41
 */
public class ValidacaoException extends RuntimeException {

    private int status;

    public ValidacaoException(String mensagem, int status) {
        super(mensagem);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

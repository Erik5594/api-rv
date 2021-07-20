package com.erik5594.apivr.domain;

import java.util.Date;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 21:38
 */
public class DetalheErro {
    private String mensagem;
    private Long timestamp;
    private int status;

    public DetalheErro(String mensagem, int status) {
        this.mensagem = mensagem;
        this.timestamp = System.currentTimeMillis();
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package com.erik5594.apivr.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 23:11
 */
public class ResultadoVotacao {

    private long qtdeVotosSim;
    private long qtdeVotosNao;
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private boolean status;

    public ResultadoVotacao(long qtdeVotosSim, long qtdeVotosNao, boolean status) {
        this.qtdeVotosSim = qtdeVotosSim;
        this.qtdeVotosNao = qtdeVotosNao;
        this.status = status;
    }

    public long getQtdeVotosSim() {
        return qtdeVotosSim;
    }

    public void setQtdeVotosSim(long qtdeVotosSim) {
        this.qtdeVotosSim = qtdeVotosSim;
    }

    public long getQtdeVotosNao() {
        return qtdeVotosNao;
    }

    public void setQtdeVotosNao(long qtdeVotosNao) {
        this.qtdeVotosNao = qtdeVotosNao;
    }

    public String getResultado() {
        StringBuilder resultado = new StringBuilder();
        if(status)
            resultado.append("Parcialmente ");
        if(qtdeVotosNao > qtdeVotosSim){
            resultado.append("Reprovado");
        }else if (qtdeVotosNao < qtdeVotosSim){
            resultado.append("Aprovado");
        }else{
            resultado.append("Empatado");
        }
        return resultado.toString();
    }

}

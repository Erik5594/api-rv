package com.erik5594.apivr.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:20
 */

@Entity
public class Pauta {

    @Id
    private String id;
    private String assunto;
    private Date dataCadastro;
    private Date inicioSessao;
    private Date fimSessao;
    @Transient
    private boolean votacaoAberta;

    public Pauta() {
        this.dataCadastro = new Date();
    }

    public Pauta(String assunto) {
        this();
        this.assunto = assunto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public Date getInicioSessao() {
        return inicioSessao;
    }

    public void setInicioSessao(Date inicioSessao) {
        this.inicioSessao = inicioSessao;
    }

    public Date getFimSessao() {
        return fimSessao;
    }

    public void setFimSessao(Date fimSessao) {
        this.fimSessao = fimSessao;
    }

    public boolean isVotacaoAberta(){
        return getInicioSessao() != null
                && getFimSessao() != null
                && getFimSessao().compareTo(new Date()) > 0
                && getInicioSessao().compareTo(new Date()) < 1;
    }

}

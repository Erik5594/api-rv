package com.erik5594.apivr.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:20
 */

@Entity
public class Pauta {

    @Id
    private String id;

    @NotEmpty(message = "O campo assunto não pode vazio.")
    private String assunto;
    private Date dataCadastro;
    private Date inicioSessao;
    private Date fimSessao;

    public Pauta() {
        this.dataCadastro = new Date();
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
}

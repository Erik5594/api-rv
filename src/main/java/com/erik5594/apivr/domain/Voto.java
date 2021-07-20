package com.erik5594.apivr.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:08
 */
@Entity
public class Voto {

    @Id
    private String id;

    @ApiModelProperty(value = "Deve ser informado SIM ou NAO", example = "SIM")
    @Enumerated(EnumType.STRING)
    private VotoEnum voto;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    @NotNull(message = "Pauta deve ser informada.")
    private Pauta pauta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf_associado")
    @NotNull(message = "Associado deve ser iformado")
    private Associado associado;

    public Voto() {
    }

    public Voto(VotoEnum voto, String idPauta, Associado associado) {
        this.voto = voto;
        Pauta pauta = new Pauta();
        pauta.setId(idPauta);
        this.pauta = pauta;
        this.associado = associado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VotoEnum getVoto() {
        return voto;
    }

    public void setVoto(VotoEnum voto) {
        this.voto = voto;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

}

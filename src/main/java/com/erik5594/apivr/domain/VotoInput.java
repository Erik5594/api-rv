package com.erik5594.apivr.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author erik_
 * Data Criacao: 20/07/2021 - 14:05
 */

@ApiModel("Voto")
public class VotoInput {

    @ApiModelProperty(value = "ID da Pauta a ser votada.", required = true)
    private String idPauta;

    @ApiModelProperty(value = "Deve ser informado SIM ou NAO", example = "SIM", required = true)
    private VotoEnum voto;

    @NotNull(message = "Associado deve ser iformado")
    private Associado associado;

    public String getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(String idPauta) {
        this.idPauta = idPauta;
    }

    public VotoEnum getVoto() {
        return voto;
    }

    public void setVoto(VotoEnum voto) {
        this.voto = voto;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }
}

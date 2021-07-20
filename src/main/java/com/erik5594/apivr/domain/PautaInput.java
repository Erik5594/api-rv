package com.erik5594.apivr.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @author erik_
 * Data Criacao: 20/07/2021 - 14:01
 */

@ApiModel("Pauta")
public class PautaInput {

    @ApiModelProperty(example = "Pauta 1", required = true)
    @NotEmpty(message = "O campo assunto não pode vazio.")
    private String assunto;

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}

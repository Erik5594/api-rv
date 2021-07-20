package com.erik5594.apivr.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:11
 */
@Entity
public class Associado {

    @Id
    @NotEmpty(message = "CPF do associado deve ser informado.")
    @ApiModelProperty(required = true)
    private String cpf;

    @NotEmpty(message = "O nome do associado deve ser informado.")
    @ApiModelProperty(required = true)
    private String nome;

    public String getCpf() {
        if(StringUtils.isNotBlank(this.cpf)){
            setCpf(cpf.replaceAll("\\D", ""));
        }
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

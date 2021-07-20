package com.erik5594.apivr.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:11
 */
@Entity
public class Associado {

    @Id
    @NotEmpty(message = "CPF do associado deve ser informado.")
    private String cpf;

    @NotEmpty(message = "O nome do associado deve ser informado.")
    private String nome;

    @OneToMany(mappedBy = "associado", fetch = FetchType.LAZY)
    private List<Voto> votos;

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

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
}

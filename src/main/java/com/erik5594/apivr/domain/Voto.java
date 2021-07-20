package com.erik5594.apivr.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:08
 */
@Entity
public class Voto {

    @Id
    private String id;

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

package com.erik5594.apivr.service.impl;

import com.erik5594.apivr.exceptions.ValidacaoException;
import com.erik5594.apivr.service.ValidaCPF;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author erik_
 * Data Criacao: 24/07/2021 - 15:07
 */

@Service
public class ValidaCPFImpl implements ValidaCPF {

    @Override
    public boolean aptoVotar(String cpf) {
        return "ABLE_TO_VOTE".equalsIgnoreCase(consultarCpf(cpf));
    }

    private String consultarCpf(String cpf){
        try {
            RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(3)).build();
            RequestEntity<Void> request = RequestEntity.get(URI.create("https://user-info.herokuapp.com//users/"+cpf)).build();
            ResponseEntity<HashMap> response = restTemplate.exchange(request, HashMap.class);
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                Map<String, String> resultado = response.getBody();
                if (resultado != null) {
                    return resultado.get("status");
                }
            }
        }catch (HttpClientErrorException e) {
            if(!HttpStatus.NOT_FOUND.equals(e.getStatusCode())){
                throw e;
            }
        }catch (Exception e){
            throw new ValidacaoException("Ocorreu um erro ao validar o CPF, tente novamente mais tarde!", 500);
        }
        throw new ValidacaoException("CPF do associado é inválido!", 400);
    }
}

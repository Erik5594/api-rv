package com.erik5594.apivr.resources;

import com.erik5594.apivr.domain.Voto;
import com.erik5594.apivr.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:19
 */
@RestController
@RequestMapping("/votos")
public class VotoResource {

    @Autowired
    private VotoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criar(@Valid @RequestBody Voto voto){
        System.out.println(voto);
        service.salvar(voto);
        return ResponseEntity.ok().build();
    }

}

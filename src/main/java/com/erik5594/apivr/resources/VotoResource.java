package com.erik5594.apivr.resources;

import com.erik5594.apivr.domain.Voto;
import com.erik5594.apivr.domain.VotoInput;
import com.erik5594.apivr.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(tags = "Votos")
@RestController
@RequestMapping("/votos")
public class VotoResource {

    @Autowired
    private VotoService service;

    @ApiOperation("Registrar um voto para uma determinada pauta.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criar(@ApiParam(name = "corpo", value = "Representação de um novo voto.")@Valid @RequestBody VotoInput votoInput){
        service.salvar(new Voto(votoInput.getVoto(), votoInput.getIdPauta(), votoInput.getAssociado()));
        return ResponseEntity.ok().build();
    }

}

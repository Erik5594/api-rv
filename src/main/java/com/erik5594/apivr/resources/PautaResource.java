package com.erik5594.apivr.resources;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:18
 */
@RestController
@RequestMapping("/pautas")
public class PautaResource {

    @Autowired
    private PautaService pautaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criar(@Valid @RequestBody Pauta pauta){
        pauta = pautaService.salvar(pauta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pauta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}

package com.erik5594.apivr.resources;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        pautaService.salvar(pauta);
        return ResponseEntity.accepted().build();
    }


    @RequestMapping(value = "/{id}/abrir-votacao", method = RequestMethod.PUT)
    public ResponseEntity<Void> abrirSessao(@PathVariable("id") String idPauta,
                                            @RequestParam(value = "segundos-aberta", required = false) Integer segundosAberta){
        pautaService.abrirSessao(idPauta, segundosAberta == null ? 0:segundosAberta);
        return ResponseEntity.accepted().build();
    }
}

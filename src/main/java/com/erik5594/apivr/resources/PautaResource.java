package com.erik5594.apivr.resources;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.domain.ResultadoVotacao;
import com.erik5594.apivr.service.PautaService;
import com.erik5594.apivr.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:18
 */
@RestController
@RequestMapping("/pautas")
public class PautaResource {

    @Autowired
    private PautaService pautaService;
    @Autowired
    private VotoService votoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pauta>> buscarTodas(){
        return ResponseEntity.ok(pautaService.buscarTodas());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pauta> buscar(@PathVariable("id") String idPauta){
        return ResponseEntity.ok(pautaService.buscar(idPauta));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criar(@Valid @RequestBody Pauta pauta){
        pauta = pautaService.salvar(pauta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pauta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}/abrir-votacao", method = RequestMethod.PUT)
    public ResponseEntity<Void> abrirSessao(@PathVariable("id") String idPauta,
                                            @RequestParam(value = "segundos-aberta", required = false) Integer segundosAberta){
        pautaService.abrirSessao(idPauta, segundosAberta == null ? 0:segundosAberta);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/{id}/resultado", method = RequestMethod.GET)
    public ResponseEntity<ResultadoVotacao> getResultado(@PathVariable("id") String idPauta){
        return ResponseEntity.ok(votoService.resultado(idPauta));
    }
}

package com.erik5594.apivr.resources;

import com.erik5594.apivr.domain.Pauta;
import com.erik5594.apivr.domain.PautaInput;
import com.erik5594.apivr.domain.ResultadoVotacao;
import com.erik5594.apivr.service.PautaService;
import com.erik5594.apivr.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(tags = "Pautas")
@RestController
@RequestMapping("/pautas")
public class PautaResource {

    @Autowired
    private PautaService pautaService;
    @Autowired
    private VotoService votoService;

    @ApiOperation("Listar todas pautas cadastradas.")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pauta>> buscarTodas(){
        return ResponseEntity.ok(pautaService.buscarTodas());
    }

    @ApiOperation("Buscar uma determinada pauta através do ID.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pauta> buscar(@ApiParam("ID de uma pauta.") @PathVariable("id") String idPauta){
        return ResponseEntity.ok(pautaService.buscar(idPauta));
    }

    @ApiOperation("Cadastrar uma nova pauta.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criar(@ApiParam(name = "corpo", value = "Representação de uma nova pauta.") @Valid @RequestBody PautaInput pautaInput){
        Pauta pauta = pautaService.salvar(new Pauta(pautaInput.getAssunto()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pauta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Abrir um sessão de votação para uma determinada pauta.")
    @RequestMapping(value = "/{id}/abrir-votacao", method = RequestMethod.PUT)
    public ResponseEntity<Void> abrirSessao(@ApiParam("ID de uma pauta.") @PathVariable("id") String idPauta,
                                            @ApiParam(value = "Representa a quantidade em segundos que a sessão da pauta deve ficar aberta para votação." +
                                                    "Caso não informado a sessão ficará aberta por 1 minuto.", example = "10")
                                            @RequestParam(value = "segundos-aberta", required = false) Integer segundosAberta){
        pautaService.abrirSessao(idPauta, segundosAberta == null ? 0:segundosAberta);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Consultar o resultado da votação de uma determinada pauta.")
    @RequestMapping(value = "/{id}/resultado", method = RequestMethod.GET)
    public ResponseEntity<ResultadoVotacao> getResultado(@ApiParam("ID de uma pauta.") @PathVariable("id") String idPauta){
        return ResponseEntity.ok(votoService.resultado(idPauta));
    }
}

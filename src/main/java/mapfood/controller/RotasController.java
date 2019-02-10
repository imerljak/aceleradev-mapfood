package mapfood.controller;

import mapfood.model.dto.ResultadoRota;
import mapfood.model.dto.SolicitacaoEntrega;
import mapfood.service.RotasService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/rotas")
public class RotasController {

    private final RotasService rotasService;

    public RotasController(RotasService rotasService) {
        this.rotasService = rotasService;
    }

    @PostMapping
    public CompletableFuture<ResultadoRota> getRotaParaPedido(@Validated @RequestBody SolicitacaoEntrega solicitacao) {
        return rotasService.getMelhorRotaPara(solicitacao);
    }

}

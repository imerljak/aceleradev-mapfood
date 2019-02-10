package mapfood.controller;

import mapfood.model.mongodb.DadosEntrega;
import mapfood.service.DadosEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
public class RelatorioEntregasController {

    private final DadosEntregaService service;

    @Autowired
    public RelatorioEntregasController(DadosEntregaService service) {
        this.service = service;
    }

    @GetMapping(value = "/relatorio/{id}", params = "dias")
    public List<DadosEntrega> getRelatorio(@PathVariable("id") String idEstabelecimento, @RequestParam("dias") int dias) {
        return service.buscaRelatorioEntregas(idEstabelecimento, dias);
    }


}

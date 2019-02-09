package mapfood.controller;

import mapfood.model.mongodb.DadosEntrega;
import mapfood.service.DadosEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="v1")
public class DadosEntregaController {

    @Autowired
    private DadosEntregaService service;

    @GetMapping("/restaurante/{id}/{daysBefore}")
    public List<DadosEntrega> getRelatorioPorIdAndData(@PathVariable String id,@PathVariable int daysBefore){return service.buscaPorIdAndAfterData(id, daysBefore);}


}

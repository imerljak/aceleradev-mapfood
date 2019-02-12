package mapfood.controller;

import mapfood.dto.EstabelecimentoDTO;
import mapfood.service.EstabelecimentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/estabelecimento")
public class EstabelecimentoController {

    private final EstabelecimentoService service;

    public EstabelecimentoController(EstabelecimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstabelecimentoDTO> getTodosEstabelecimentos() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public EstabelecimentoDTO findById(@PathVariable String id) {
        return service.findById(id);
    }
}

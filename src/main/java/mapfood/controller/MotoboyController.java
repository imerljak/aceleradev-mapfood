package mapfood.controller;

import mapfood.model.dto.MotoboyDTO;
import mapfood.service.MotoboyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/motoboy")
public class MotoboyController {

    private final MotoboyService service;

    public MotoboyController(MotoboyService service) {
        this.service = service;
    }

    @GetMapping
    public List<MotoboyDTO> getMotoboys() {
        return service.buscaTodos();
    }

    @GetMapping("/{id}")
    public Optional<MotoboyDTO> getMotoboysPorId(@PathVariable Long id) {
        return service.buscaPorId(id);
    }

}

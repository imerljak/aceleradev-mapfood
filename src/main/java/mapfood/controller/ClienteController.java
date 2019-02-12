package mapfood.controller;

import mapfood.dto.ClienteDTO;
import mapfood.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDTO> getClientes() {
        return service.buscaTodos();
    }

    @GetMapping("/{id}")
    public ClienteDTO getClientesPorId(@PathVariable Long id) {
        return service.buscaPorId(id);
    }

}

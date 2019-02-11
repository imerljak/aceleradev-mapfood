package mapfood.controller;

import mapfood.service.RelatorioEntregasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class RelatorioEntregasController {

    private final RelatorioEntregasService service;

    @Autowired
    public RelatorioEntregasController(RelatorioEntregasService service) {
        this.service = service;
    }

    @GetMapping(value = "/relatorio/{id}")
    public ResponseEntity<?> getRelatorio(@PathVariable("id") String idEstabelecimento,
                                          @RequestParam(value = "dias", defaultValue = "30", required = false) int dias) {
        return ResponseEntity.ok(service.getRelatorioEntregas(idEstabelecimento, dias));
    }


}

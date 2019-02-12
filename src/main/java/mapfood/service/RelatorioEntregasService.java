package mapfood.service;

import mapfood.dto.RelatorioEntrega;
import mapfood.model.mongodb.Entrega;

public interface RelatorioEntregasService {

    RelatorioEntrega getRelatorioEntregas(String id, int days);

    void salvar(Entrega entrega);
}

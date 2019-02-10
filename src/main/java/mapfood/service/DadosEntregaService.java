package mapfood.service;

import mapfood.model.mongodb.DadosEntrega;

import java.util.List;

public interface DadosEntregaService {

    List<DadosEntrega> buscaRelatorioEntregas(String id, int days);

    void salvar(DadosEntrega dadosEntrega);
}

package mapfood.service;

import mapfood.exceptions.MotoboyNaoEncontradoException;
import mapfood.factory.MotoboyFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Posicao;
import mapfood.repository.sql.MotoboyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoboyServiceImpl implements MotoboyService {

    private final Logger logger = LoggerFactory.getLogger(MotoboyServiceImpl.class);

    private final MotoboyRepository repository;

    public MotoboyServiceImpl(MotoboyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MotoboyDTO> buscaTodos() {
        logger.info("Buscando todos motoboys.");

        return repository.findAll()
                .parallelStream()
                .map(MotoboyFactory::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    public MotoboyDTO buscaMaisProximo(Posicao posicao, Double raioEmKm) {
        logger.info("Buscando motoboy mais proximo de: {} - no raio (km): {}", posicao, raioEmKm);

        return repository.buscaMaisProximo(posicao.getLatitude(), posicao.getLongitude())
                .map(MotoboyFactory::getInstance)
                .orElseThrow(() ->
                        new MotoboyNaoEncontradoException("Não foi possível encontrar um motoboy na região"));
    }

    @Override
    public MotoboyDTO buscaPorId(Long id) {
        logger.info("Buscando motoboy por id: {}", id);

        return repository
                .findById(id)
                .map(MotoboyFactory::getInstance)
                .orElseThrow(() ->
                        new MotoboyNaoEncontradoException("Não foi encontrado nenhum motoboy com este id: " + id));
    }

}

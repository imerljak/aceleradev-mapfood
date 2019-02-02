package mapfood.data;

import mapfood.csv.LeitorDeCsv;
import mapfood.factory.MotoboyFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Motoboy;
import mapfood.repository.sql.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportarDadosMotoboys implements ImportadorDeDados {

    private final MotoboyRepository repository;

    @Autowired
    public ImportarDadosMotoboys(MotoboyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void importar() {
        if (repository.count() == 0) {
            List<Motoboy> motoboys = new LeitorDeCsv().lerDados(MotoboyDTO.class, "/csv/motoboys.csv")
                    .parallelStream()
                    .map(MotoboyFactory::getInstance)
                    .collect(Collectors.toList());

            repository.saveAll(motoboys);
        }
    }

    @Override
    public void proximoPasso() {
        // Faz nada..
    }
}

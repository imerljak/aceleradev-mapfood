package mapfood.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImportacaoDeDadosChain implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(ImportacaoDeDadosChain.class);

    private final List<ImportadorDeDados> importadores;

    @Autowired
    public ImportacaoDeDadosChain(List<ImportadorDeDados> importadores) {
        this.importadores = importadores;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Aplicação iniciada, começando importação de dados.");
        importadores.forEach(ImportadorDeDados::importar);
    }

}

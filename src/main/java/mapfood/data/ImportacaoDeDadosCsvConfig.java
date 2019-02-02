package mapfood.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ImportacaoDeDadosCsvConfig implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(ImportacaoDeDadosCsvConfig.class);

    private final ImportadorDeDados importador;

    @Autowired
    public ImportacaoDeDadosCsvConfig(@Qualifier("importarDadosClientes") ImportadorDeDados importador) {
        this.importador = importador;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Aplicação iniciada, começando importação de dados.");

        importador.importar();
    }
}

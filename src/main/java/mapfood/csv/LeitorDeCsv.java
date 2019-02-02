package mapfood.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import mapfood.data.ImportacaoDeDadosCsvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Helper para parsing e importação de dados de arquivos CSV.
 */
public class LeitorDeCsv {

    private Logger logger = LoggerFactory.getLogger(ImportacaoDeDadosCsvConfig.class);


    public <T> List<T> lerDados(Class<T> tClass, String filename) {
        try {

            logger.debug("Iniciando parsing de CSV.");

            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();

            File file = new ClassPathResource(filename).getFile();

            logger.debug("Arquivo aberto: {}", file);

            MappingIterator<T> iterator = mapper.readerFor(tClass).with(schema).readValues(file);

            return iterator.readAll();
        } catch (Exception e) {
            //TODO: Log error.

            logger.error(e.getMessage(), e);

            return Collections.emptyList();
        }
    }

}

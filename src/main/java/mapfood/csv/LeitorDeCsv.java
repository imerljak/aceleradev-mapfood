package mapfood.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import mapfood.data.ImportacaoDeDadosChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Helper para parsing e importação de dados de arquivos CSV.
 */
@Component
public class LeitorDeCsv {

    private Logger logger = LoggerFactory.getLogger(ImportacaoDeDadosChain.class);

    public <T> List<T> lerDados(Class<T> tClass, String filename) {
        try {
            final CsvSchema schema = CsvSchema.emptySchema().withHeader();
            final CsvMapper mapper = new CsvMapper();
            final File file = new ClassPathResource(filename).getFile();
            final MappingIterator<T> iterator = mapper.readerFor(tClass).with(schema).readValues(file);

            return iterator.readAll();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

}
